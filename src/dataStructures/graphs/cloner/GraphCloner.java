package dataStructures.graphs.cloner;

import dataStructures.graphs.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCloner {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n2.neighbors = List.of(n1);
        Node root = new Node(0);
        root.neighbors = List.of(n1, n2);
        System.out.println("OG:    " + root);
        System.out.println("Clone: " + cloneGraph(root));
    }

    // DFS
    public static Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();

        if (node == null) return null;

        if (map.containsKey(node)) {
            return map.get(node); // already cloned, use the previously cloned one
        }

        // Clone the node before recursion
        Node clone = new Node(node.val); // create new node with same value
        map.put(node, clone);

        // Clone all the neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}
