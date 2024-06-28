package com.metaxiii.fr;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class App {

  public Set<Integer> findModeByFrequencyArray(final int[] nums) {
    final Map<Integer, Integer> frequencyMap = new HashMap<>();
    return findMode(nums, frequencyMap);
  }

  private Set<Integer> findMode(final int[] nums, final Map<Integer, Integer> frequencyMap) {
    for (final int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }
    int maxFrequency = 0;
    for (final int frequency : frequencyMap.values()) {
      if (frequency > maxFrequency) {
        maxFrequency = frequency;
      }
    }
    final Set<Integer> modes = new HashSet<>();
    for (final Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      if (entry.getValue() == maxFrequency) {
        modes.add(entry.getKey());
      }
    }
    return modes;
  }

  public Set<Integer> findModeBySorting(final int[] nums) {
    Arrays.sort(nums);
    int maxCount = 1;
    int currentCount = 1;
    final Set<Integer> modes = new HashSet<>();

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        currentCount++;
      } else {
        currentCount = 1;
      }

      if (currentCount > maxCount) {
        maxCount = currentCount;
        modes.clear();
        modes.add(nums[i]);
      } else if (currentCount == maxCount) {
        modes.add(nums[i]);
      }
    }

    if (nums.length == 1) {
      modes.add(nums[0]);
    }
    return modes;
  }

  public Set<Integer> findModeByUsingStreams(final int[] nums) {
    final var frequencyMap = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    final var maxFrequency = Collections.max(frequencyMap.values());
    return frequencyMap
      .entrySet()
      .stream()
      .filter(entry -> entry.getValue().equals(maxFrequency))
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
  }

  public Set<Integer> findModeByUsingTreemap(final int[] nums) {
    final Map<Integer, Integer> frequencyMap = new TreeMap<>();
    return findMode(nums, frequencyMap);
  }
}
