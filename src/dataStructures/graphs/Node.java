package dataStructures.graphs;

import java.util.ArrayList;
import java.util.List;

// LeetCode Node for examples
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    // My addition: toString()

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", neighbors=" + neighbors +
                '}';
    }
}
