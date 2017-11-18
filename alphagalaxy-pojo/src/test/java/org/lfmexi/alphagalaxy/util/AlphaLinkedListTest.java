package org.lfmexi.alphagalaxy.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AlphaLinkedListTest {

  @Test
  public void createStringLinkedList() {
    AlphaLinkedList<String> strings = new AlphaLinkedList<String>();
    assertNotNull(strings);
  }

  @Test
  public void populateStringLinkedList() {
    AlphaLinkedList<String> strings = new AlphaLinkedList<String>();
    strings.add("first");
    strings.add("second");

    assertEquals(2, strings.size());
  }

  @Test
  public void getTest() {
    AlphaLinkedList<String> strings = new AlphaLinkedList<String>();
    strings.add("first");
    strings.add("second");

    assertEquals("first", strings.get(0));
  }

  @Test
  public void findAStringTest() {
    AlphaLinkedList<String> strings = new AlphaLinkedList<String>();
    strings.add("first");
    strings.add("second");

    assertEquals("second", strings.find("second"));
  }

  @Test
  public void convertToArray() {
    AlphaLinkedList<String> strings = new AlphaLinkedList<String>();
    strings.add("first");
    strings.add("second");

    String[] stringArray = strings.toArray(String.class);
    assertNotNull(stringArray);
    assertEquals(2, stringArray.length);
  }
}
