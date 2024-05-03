package com.metaxiii.fr.model;

public class Post {

  private final String title;
  private final String text;
  private final String category;

  public Post(final Builder builder) {
    this.title = builder.title;
    this.text = builder.text;
    this.category = builder.category;
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

    public Post build() {
      return new Post(this);
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
