# Nullness-Demo
Nullness demo
  
```
./gradlew assemble
Parallel execution is an incubating feature.
:compileJava
/Users/harshitbangar/test/src/main/java/com/Arbit.java:25: error: [NullAway] @NonNull field requiredField not initialized
    @Nonnull private String requiredField;
                            ^
    (see http://t.uber.com/nullaway )
1 error
:compileJava FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':compileJava'.
> Compilation failed with exit code 1; see the compiler error output for details.

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED


Total time: 2.799 secs
```
