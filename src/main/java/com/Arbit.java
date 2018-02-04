package com;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Arbit {
  @Nonnull private final String requiredField;
  @Nullable private final String optionalField;

  private Arbit(Builder builder) {
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
    @Nonnull private String requiredField;
    @Nullable private String optionalField;

    public Builder requiredField(@Nonnull String requiredField) {
      this.requiredField = requiredField;
      return this;
    }

    public Builder optionalField(@Nullable String optionalField) {
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
