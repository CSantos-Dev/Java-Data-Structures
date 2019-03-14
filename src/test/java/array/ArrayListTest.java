package array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ArrayList implementation test cases
 * @author csantos
 */
public class ArrayListTest {

    private ArrayList<String> list;

    @Before
    public void initialize() {
        this.list = new ArrayList<>(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addThrowsExceptionIfGivenIndexIsOutOfBounds() {
        list.add("1", 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getThrowsExceptionIfGivenIndexIsOutOfBounds() {
        list.get(1);
    }

    @Test
    public void getRetrievesCorrectElementForGivenIndex() {
        list.add("1");
        assertEquals("1", list.get(0));
    }

    @Test
    public void addInsertsElementAtGivenIndex() {
        list.add("1");
        list.add("2");
        list.add("3", 1);

        assertEquals("3", list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    public void addAtGivenIndexRearrangeIndexes(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("New Element", 0);

        assertEquals("New Element", list.get(0));
        assertEquals("1", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("3", list.get(3));
        assertEquals("4", list.get(4));
        assertEquals(5, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setThrowsExceptionIfGivenIndexIsOutOfBounds() {
        list.add("1");
        list.set("2", 1);
    }

    @Test
    public void setChangesTheValueAtGivenIndex() {
        list.add("1");
        list.set("One", 0);

        assertEquals("One", list.get(0));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void removeThrowsExceptionIfGivenIndexIsOutOfBounds() {
        list.remove(1);
    }

    @Test
    public void removeDeletesElementFromTheList() {
        list.add("1");
        assertEquals(1, list.size());

        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    public void removeRearrangeIndexes() {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.remove(1);

        assertEquals("1", list.get(0));
        assertEquals("3", list.get(1));
        assertEquals("4", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void containsReturnTrueGivenItemIsPresent() {
        list.add("1");
        assertTrue(list.contains("1"));
    }

    @Test
    public void containsReturnFalseIfGivenItemIsAbsent() {
        list.add("1");
        assertFalse(list.contains("2"));
    }

}
