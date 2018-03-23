package com.benchmark;


import com.benchmark.model.RidiculouslyBigUser;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import okio.BufferedSource;
import okio.Okio;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmarks different approaches for parsing simple json objects. All json parsers are used with
 * default settings.
 *
 * @author Serj Lotutovici
 */
@SuppressWarnings("unused")
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class Benchmarker {

  private Moshi moshi;
  private RbuMoshiStreamingParser moshiStreamingParser;
  private Gson gson;
  private Kryo kryo;

  @Setup(Level.Trial)
  public void setup() {
    moshi = new Moshi.Builder().build();
    moshiStreamingParser = new RbuMoshiStreamingParser();
    gson = new Gson();
    kryo = new Kryo();
  }

  @Benchmark
  public RidiculouslyBigUser moshiReflection() throws IOException {
    try (InputStream is = getTestDataStream()) {
      BufferedSource dataSource = getSourceFromStream(is);
      JsonAdapter<RidiculouslyBigUser> userJsonAdapter = moshi.adapter(RidiculouslyBigUser.class);
      return userJsonAdapter.fromJson(dataSource);
    }
  }

  @Benchmark
  public RidiculouslyBigUser moshiStreaming() throws IOException {
    try (InputStream is = getTestDataStream()) {
      BufferedSource dataSource = getSourceFromStream(is);
      return moshiStreamingParser.readJsonStream(dataSource);
    }
  }

  @Benchmark
  public RidiculouslyBigUser kryoBenchmarking() throws IOException {
    try (Input input = new Input(getTestDataStreamForKryo())) {
      return kryo.readObject(input, RidiculouslyBigUser.class);
    }
  }

  @Benchmark
  public RidiculouslyBigUser gsonReflection() throws IOException {
    try (InputStreamReader reader = new InputStreamReader(getTestDataStream())) {
      return gson.fromJson(reader, RidiculouslyBigUser.class);
    }
  }

  /** Creates a {@link BufferedSource} from provided stream */
  BufferedSource getSourceFromStream(InputStream is) throws FileNotFoundException {
    return Okio.buffer(Okio.source(is));
  }

  /** Open the test data file stream */
  private InputStream getTestDataStream() {
    ClassLoader classLoader = getClass().getClassLoader();
    return classLoader.getResourceAsStream("test_data.json");
  }

  private InputStream getTestDataStreamForKryo() {
    ClassLoader classLoader = getClass().getClassLoader();
    return classLoader.getResourceAsStream("test_data.bin");
  }
}
