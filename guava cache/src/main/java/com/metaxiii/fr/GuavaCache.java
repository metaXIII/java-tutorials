package com.metaxiii.fr;

import com.google.common.cache.CacheLoader;
import org.springframework.lang.NonNull;

public class GuavaCache {

  private final CacheLoader<String, String> loader;

  public GuavaCache() {
    loader =
      new CacheLoader<>() {
        @Override
        public String load(@NonNull String key) {
          return key.toUpperCase();
        }
      };
  }

  public CacheLoader<String, String> getLoader() {
    return loader;
  }
}
