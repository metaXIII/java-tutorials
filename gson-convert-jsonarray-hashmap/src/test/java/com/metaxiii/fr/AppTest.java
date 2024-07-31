package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

  private JsonArray jsonArray;

  @Test
  void itShouldConvertWithHashMap() {
    final Map<String, Integer> hashMap = convertUsingIterative(jsonArray);

    assertEquals(35, hashMap.get("John Doe"));
    assertEquals(41, hashMap.get("Mary Jenn"));
  }

  Map<String, Integer> convertUsingIterative(final JsonArray jsonArray) {
    final Map<String, Integer> hashMap = new HashMap<>();
    for (final JsonElement element : jsonArray) {
      final JsonObject jsonObject = element.getAsJsonObject();
      final String type = jsonObject.get("name").getAsString();
      final Integer amount = jsonObject.get("age").getAsInt();
      hashMap.put(type, amount);
    }
    return hashMap;
  }

  @Test
  void itShouldConvertWithStreamsApproach() {
    final Map<String, Integer> hashMap = convertUsingStreams(jsonArray);
    assertEquals(35, hashMap.get("John Doe"));
    assertEquals(41, hashMap.get("Mary Jenn"));
  }

  Map<String, Integer> convertUsingStreams(final JsonArray jsonArray) {
    return StreamSupport
      .stream(jsonArray.spliterator(), false)
      .map(JsonElement::getAsJsonObject)
      .collect(
        Collectors.toMap(
          jsonObject -> jsonObject.get("name").getAsString(),
          jsonObject -> jsonObject.get("age").getAsInt()
        )
      );
  }

  @BeforeEach
  void setUp() {
    jsonArray = new JsonArray();

    final JsonObject jsonObject1 = new JsonObject();
    jsonObject1.addProperty("name", "John Doe");
    jsonObject1.addProperty("age", 35);
    jsonArray.add(jsonObject1);

    final JsonObject jsonObject2 = new JsonObject();
    jsonObject2.addProperty("name", "Mary Jenn");
    jsonObject2.addProperty("age", 41);
    jsonArray.add(jsonObject2);
  }
}
