package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * Implementation of a undirected graph using adjacent list
 * @author csantos
 */
public class Graph<T> {

    private Map<T, List<T>> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    /**
     * Inserts the given element in this graph it is not already present
     */
    public void add(T data) {
        Objects.requireNonNull(data);
        nodes.putIfAbsent(data, new ArrayList<>());
    }

    /**
     * Removes the given node from the graph
     */
    public void removeNode(T data) {
        nodes.values().stream().forEach(l -> l.remove(data));
        nodes.remove(data);
    }

    /**
     * Connect the given nodes in the graph
     */
    public void connect(T nodeOne, T nodeTwo) {
        if(!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            throw new RuntimeException("Given nodes are not present in the graph");
        }

        if(!areConnected(nodeOne, nodeTwo)) {
            nodes.get(nodeOne).add(nodeTwo);
            nodes.get(nodeTwo).add(nodeOne);
        }
    }

    /**
     * Removes the connection between given nodes
     */
    public void removeConnection(T nodeOne, T nodeTwo) {
        nodes.get(nodeOne).remove(nodeTwo);
        nodes.get(nodeTwo).remove(nodeOne);
    }

    /**
     * Returns a list of the adjacent nodes or an empty list
     * if the given node is not connected to any other node
     */
    public List<T> getAdjacentNodes(T nodeOne) {
        if(!nodes.containsKey(nodeOne)) {
            throw new RuntimeException("The graph does not contain the given node");
        }
        return nodes.get(nodeOne);
    }

    /**
     * Returns whether the given element is present in the graph or not
     */
    public boolean containsNode(T data) {
        return nodes.containsKey(data);
    }

    /**
     * Returns whether there is an edge between given nodes
     */
    public boolean areConnected(T nodeOne, T nodeTwo) {
        return nodes.get(nodeOne).contains(nodeTwo);
    }

    /**
     * Return the amount of nodes
     */
    public int countNodes() {
        return nodes.size();
    }

}
