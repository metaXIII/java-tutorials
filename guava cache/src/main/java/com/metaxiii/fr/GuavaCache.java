package com.metaxiii.fr;

import com.google.common.cache.CacheLoader;

public class GuavaCache {
  private final CacheLoader<String, String> loader;

  public GuavaCache() {
    loader =
      new CacheLoader<>() {

        @Override
        public String load(String key) {
          return key.toUpperCase();
        }
      };
  }

  public CacheLoader<String, String> getLoader() {
    return loader;
  }
}
