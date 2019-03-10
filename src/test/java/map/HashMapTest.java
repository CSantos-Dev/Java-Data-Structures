package map;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Test class for test cases of HashMap implementation
 * @author csantos
 */
public class HashMapTest {

    private HashMap<Integer, String> map;

    @Before
    public void initializeForEachTest() {
        map = new HashMap<>();
    }

    @Test
    public void severalElementsCanBeInserted() {
        IntStream.rangeClosed(1, 20).forEach(n -> map.put(n, n + ""));

        assertEquals(20, map.size());
    }

    @Test
    public void getReturnsValueAssociatedToGivenKey() {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        assertEquals("One", map.get(1));
        assertEquals("Two", map.get(2));
        assertEquals("Three", map.get(3));
    }

    @Test
    public void getReturnsNullForInexistentKey() {
        assertNull(map.get(1));
    }

    @Test
    public void valueOverwrittenIfKeyAlreadyPresent() {
        map.put(1, "One");
        map.put(1, "1");

        assertEquals("1", map.get(1));
    }

    @Test
    public void pairsCanBeRemoved() {
        map.put(1, "One");
        assertEquals("One", map.get(1));
        assertFalse(map.isEmpty());

        map.remove(1);
        assertNull(map.get(1));
        assertTrue(map.isEmpty());
    }

    @Test
    public void removeReturnsFalseIfElementCouldNotBeRemoved() {
        boolean couldBeRemoved = map.remove(1);
        assertFalse(couldBeRemoved);
    }

    @Test
    public void sizeCorrectlyUpdated() {
        IntStream.rangeClosed(1, 5).forEach( n -> map.put(n, n + ""));
        map.remove(1);
        map.remove(3);

        assertEquals(3, map.size());
    }

    @Test
    public void containsKeyReturnsTrueIfGivenKeyIsPresent() {
        map.put(1, "1");
        assertTrue(map.containsKey(1));
    }

    @Test
    public void containsKeyReturnsFalseIfGivenKeyIsNotPresent() {
        map.put(1, "1");
        assertFalse(map.containsKey(2));
    }


    @Test
    public void mapWorksWithBadHashcodeImplementation() {
        HashMap<MockKey, String> dummyMap = new HashMap<>();
        dummyMap.put(new MockKey(1), "1");
        dummyMap.put(new MockKey(2), "2");
        dummyMap.put(new MockKey(3), "3");
        dummyMap.put(new MockKey(4), "4");
        dummyMap.put(new MockKey(5), "5");

        assertEquals(5, dummyMap.size());
        assertEquals("1", dummyMap.get(new MockKey(1)));
        assertEquals("2", dummyMap.get(new MockKey(2)));
        assertEquals("3", dummyMap.get(new MockKey(3)));
        assertEquals("4", dummyMap.get(new MockKey(4)));
        assertEquals("5", dummyMap.get(new MockKey(5)));

        dummyMap.remove(new MockKey(2));
        dummyMap.remove(new MockKey(4));

        assertEquals(3, dummyMap.size());
        assertEquals("1", dummyMap.get(new MockKey(1)));
        assertNull((dummyMap.get(new MockKey(2))));
        assertEquals("3", dummyMap.get(new MockKey(3)));
        assertNull(dummyMap.get(new MockKey(4)));
        assertEquals("5", dummyMap.get(new MockKey(5)));
    }


    /**
     * Dummy class to test HashMap using bad hashcode implementation
     */
    private class MockKey {
        private int id;

        private MockKey(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof  MockKey && id == ((MockKey) obj).id);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
