package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.Weigher;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuavaCacheTest {
  private GuavaCache guavaCache;

  @BeforeEach
  public void initAll() {
    guavaCache = new GuavaCache();
  }

  @AfterEach
  public void endEach() {
    guavaCache = null;
  }

  @Test
  void whenCacheMiss_thenValueIsComputed() {
    final var cache = CacheBuilder.newBuilder().build(guavaCache.getLoader());
    assertEquals(0, cache.size());
    assertEquals("HELLO", cache.getUnchecked("hello"));
    assertEquals(1, cache.size());
  }

  @Test
  void whenCacheReachMaxSize_thenEviction() {
    final var cache = CacheBuilder.newBuilder().maximumSize(3).build(guavaCache.getLoader());
    cache.getUnchecked("first");
    cache.getUnchecked("second");
    cache.getUnchecked("third");
    cache.getUnchecked("forth");
    assertEquals(3, cache.size());
    assertNull(cache.getIfPresent("first"));
    assertEquals("FORTH", cache.getIfPresent("forth"));
  }

  @Test
  void whenCacheReachMaxWeight_thenEviction() {
    final Weigher<String, String> weighByLength = (key, value) -> value.length();
    final var cache = CacheBuilder.newBuilder().maximumWeight(16).weigher(weighByLength).build(guavaCache.getLoader());
    cache.getUnchecked("first");
    cache.getUnchecked("second");
    cache.getUnchecked("third");
    cache.getUnchecked("last");
    assertEquals(3, cache.size());
    assertNull(cache.getIfPresent("first"));
    assertEquals("LAST", cache.getIfPresent("last"));
  }

  @Test
  void whenEntryIdle_thenEviction() throws InterruptedException {
    final var cache = CacheBuilder
      .newBuilder()
      .expireAfterAccess(2, TimeUnit.MILLISECONDS)
      .build(guavaCache.getLoader());
    cache.getUnchecked("hello");
    assertEquals(1, cache.size());
    cache.getUnchecked("hello");
    Thread.sleep(300);
    cache.getUnchecked("test");
    assertEquals(1, cache.size());
    assertNull(cache.getIfPresent("hello"));
  }

  @Test
  void whenEntryLiveTimeExpire_thenEviction() throws InterruptedException {
    final var cache = CacheBuilder
      .newBuilder()
      .expireAfterWrite(2, TimeUnit.MILLISECONDS)
      .build(guavaCache.getLoader());
    cache.getUnchecked("hello");
    assertEquals(1, cache.size());
    Thread.sleep(300);
    cache.getUnchecked("test");
    assertEquals(1, cache.size());
    assertNull(cache.getIfPresent("hello"));
  }

  @Test
  void whenWeakKeyHasNoRef_thenRemoveFromCache() {
    final var cache = CacheBuilder.newBuilder().weakKeys().build(guavaCache.getLoader());
    assertNotNull(cache);
  }

  @Test
  void whenSoftValue_thenRemoveFromCache() {
    final var cache = CacheBuilder.newBuilder().softValues().build(guavaCache.getLoader());
    assertNotNull(cache);
  }

  @Test
  void whenNullValue_thenOptional() {
    CacheLoader<String, Optional<String>> loader;
    loader =
      new CacheLoader<>() {

        @Override
        public Optional<String> load(String key) {
          return Optional.ofNullable(getSuffix(key));
        }
      };
    LoadingCache<String, Optional<String>> cache;
    cache = CacheBuilder.newBuilder().build(loader);
    assertEquals("txt", cache.getUnchecked("text.txt").orElse(null));
    assertFalse(cache.getUnchecked("hello").isPresent());
  }

  @Test
  void whenLiveTimeEnd_thenRefresh() {
    final var cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).build(guavaCache.getLoader());
    assertNotNull(cache);
  }

  @Test
  void whenPreloadCache_thenUsePutAll() {
    final var cache = CacheBuilder.newBuilder().build(guavaCache.getLoader());
    Map<String, String> map = new HashMap<>();
    map.put("first", "FIRST");
    map.put("second", "SECOND");
    cache.putAll(map);
    assertEquals(2, cache.size());
  }

  @Test
  void whenEntryRemovedFromCache_thenNotify() {
    final RemovalListener<String, String> listener;
    listener =
      n -> {
        if (n.wasEvicted()) {
          String cause = n.getCause().name();
          assertEquals(RemovalCause.SIZE.toString(), cause);
        }
      };
    final var cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(listener).build(guavaCache.getLoader());
    cache.getUnchecked("first");
    cache.getUnchecked("second");
    cache.getUnchecked("third");
    cache.getUnchecked("last");
    assertEquals(3, cache.size());
  }

  private String getSuffix(final String str) {
    final int lastIndex = str.lastIndexOf('.');
    if (lastIndex == -1) {
      return null;
    }
    return str.substring(lastIndex + 1);
  }
}
