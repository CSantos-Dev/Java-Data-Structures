package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Graph implementation test cases
 * @author csantos
 */
public class GraphTest {

    private Graph<String> graph;

    @Before
    public void initialize() {
        this.graph = new Graph<>();
    }

    @Test(expected = NullPointerException.class)
    public void addNodeThrowsExceptionIfNullIsAdded() {
        graph.add(null);
    }

    @Test(expected = RuntimeException.class)
    public void connectThrowsExceptionIfAnyGivenNodeIsNotPresentInGraph() {
        graph.connect("One", "Two");
    }

    @Test
    public void containsNodeReturnsTrueIfElementIsPresent() {
        graph.add("One");
        assertTrue(graph.containsNode("One"));
    }

    @Test
    public void containsNodeReturnsFalseIfElementIsAbsent() {
        graph.add("One");
        assertFalse(graph.containsNode("Two"));
    }

    @Test
    public void areConnectedReturnsTrueIfGivenNodesAreConnected() {
        createNodesAndConnect("nodeOne", "nodeTwo");

        assertTrue(graph.areConnected("nodeOne", "nodeTwo"));
    }

    @Test
    public void areConnectedReturnsFalseIfGivenNodesAreNotConnected() {
        graph.add("nodeOne");
        graph.add("nodeTwo");

        assertFalse(graph.areConnected("nodeOne", "nodeTwo"));
    }

    @Test(expected = RuntimeException.class)
    public void getAdjacentNodesThrowsExceptionIfNodeIsNotPresent() {
        graph.getAdjacentNodes("two");
    }

    @Test
    public void getAdjacentNodesReturnsNullListIfGivenNodeHasNoConnections() {
        graph.add("one");
        assertTrue(graph.getAdjacentNodes("one").isEmpty());
    }

    @Test
    public void getAdjacentNodesReturnsAdjacentNodes() {
        createNodesAndConnect("one", "two");

        assertEquals(1, graph.getAdjacentNodes("one").size());
        assertEquals("two", graph.getAdjacentNodes("one").get(0));
        assertEquals(1, graph.getAdjacentNodes("two").size());
        assertEquals("one", graph.getAdjacentNodes("two").get(0));
    }

    @Test
    public void removeNodeRemovesGivenNodeAndHisEdges() {
        createNodesAndConnect("one", "two");
        graph.removeNode("one");

        assertFalse(graph.containsNode("one"));
        assertTrue(graph.containsNode("two"));
        assertTrue(graph.getAdjacentNodes("two").isEmpty());
        assertEquals(1, graph.countNodes());
    }

    @Test
    public void removeConnectionRemovesEdgeBetweenGivenNodes() {
        createNodesAndConnect("one", "two");
        graph.removeConnection("one", "two");

        assertTrue(graph.containsNode("two"));
        assertTrue(graph.containsNode("one"));
        assertFalse(graph.areConnected("one", "two"));
    }

    private void createNodesAndConnect(String one, String two) {
        graph.add(one);
        graph.add(two);
        graph.connect(one, two);
    }
}
