package com.metaxiii.fr.model;

public class PersonalPost {

  private final String title;
  private final String text;
  private final String category;

  public PersonalPost(final Builder builder) {
    validateFields(builder);
    this.title = builder.title;
    this.text = builder.text;
    this.category = builder.category;
  }

  private void validateFields(final Builder builder) {
    if (builder.title == null) {
      throw new NullPointerException("title is null");
    }
    if (builder.text == null) {
      throw new NullPointerException("text is null");
    }
    if (builder.category == null) {
      throw new NullPointerException("category is null");
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getCategory() {
    return category;
  }

  public String getText() {
    return text;
  }

  public String getTitle() {
    return title;
  }

  public static class Builder {

    private String title;
    private String text;
    private String category;

    public PersonalPost build() {
      return new PersonalPost(this);
    }

    public Builder category(final String category) {
      this.category = category;
      return this;
    }

    public Builder text(final String text) {
      this.text = text;
      return this;
    }

    public Builder title(final String title) {
      this.title = title;
      return this;
    }
  }
}
