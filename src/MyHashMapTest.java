import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap<Integer, String> map;

    int numberOfElements = 10000;

    @Before
    public void setUp() {
        int numberOfElements = 10000;
        map = new MyHashMap<>();

        for (int i = 0; i < numberOfElements; i++) {
            map.put(i, "Value " + i);
        }
    }

    @Test
    public void shouldPutEntries() {
        assertEquals(numberOfElements, map.size());
    }

    @Test
    public void shouldGetEntries() {
        for (int i = 0; i < numberOfElements; i++) {
            assertEquals("Value " + i, map.get(i));
        }
    }

    @Test
    public void shouldRemoveEntriesAndGiveRightValuesOfSize() {
        for (int i = 0; i < numberOfElements; i++) {
            map.remove(i);
        }

        assertEquals(0, map.size());

        assertTrue(map.isEmpty());
    }

    @Test
    public void testContainKey() {
        for (int i = 0; i < numberOfElements; i++) {
            assertTrue(map.containKey(i));
        }

        assertFalse(map.containKey(1000000));
    }

    @Test
    public void testContainValues() {
        for (int i = 0; i < numberOfElements; i++) {
            assertTrue(map.containValue("Value " + i));
        }

        assertFalse(map.containKey(1000000));
    }

    @Test
    public void shouldClearMap() {
        assertFalse(map.isEmpty());

        map.clear();

        assertEquals(0, map.size());

        assertTrue(map.isEmpty());
    }

    @Test
    public void shouldReturnSetOfKeys() {
        assertEquals(numberOfElements, map.keySet().size());

        for (int i = 0; i < numberOfElements; i++) {
            assertTrue(map.keySet().contains(i));
        }
    }

    @Test
    public void shouldReturnListOfValues() {
        assertEquals(numberOfElements, map.values().size());

        for (int i = 0; i < numberOfElements; i++) {
            assertTrue(map.values().contains("Value " + i));
        }
    }

    @Test
    public void shouldReturnEntrySet() {
        assertEquals(numberOfElements, map.entrySet().size());

        for (int i = 0; i < numberOfElements; i++) {
            assertTrue(map.entrySet().contains((new MyEntry<>(i, "Value " + i))));
        }
    }

    @Test
    public void shouldIncreaseCapacity() {
        MyHashMap<Integer, String> testMap = new MyHashMap<>();
        int defaultCapacity = 16;

        int newCapacity = (int) (defaultCapacity * 1.5 + 1);

        for (int i = 0; i < newCapacity; i++) {
            testMap.put(i, "Value " + i);
        }

        assertEquals(testMap.size(), newCapacity);
    }
}
