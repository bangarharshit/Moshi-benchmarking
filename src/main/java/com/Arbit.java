package com;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Arbit {
  @Nonnull private final String requiredField;
  @Nullable private final String optionalField;

  private Arbit(Builder builder) {
    if (builder.requiredField == null) {
      throw new IllegalStateException("Required Field required");
    }
    this.requiredField = builder.requiredField;
    this.optionalField = builder.optionalField;
  }

  public String getRequiredField() {
    return requiredField;
  }

  public Optional<String> getOptionalField() {
    return Optional.ofNullable(optionalField);
  }

  public static class Builder {
    private String requiredField;
    private String optionalField;

    public Builder requiredField(String requiredField) {
      this.requiredField = requiredField;
      return this;
    }

    public Builder optionalField(String optionalField) {
      this.optionalField = optionalField;
      return this;
    }

    public Builder fromPrototype(Arbit prototype) {
      requiredField = prototype.requiredField;
      optionalField = prototype.optionalField;
      return this;
    }

    public Arbit build() {
      return new Arbit(this);
    }
  }
}
