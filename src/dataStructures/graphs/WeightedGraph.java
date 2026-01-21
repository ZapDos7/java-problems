package dataStructures.graphs;

import java.util.*;

import dataStructures.graphs.CustomGraph.Vertex;

public class WeightedGraph {
    private Map<Vertex, List<Edge>> adjVertices;

    public static class Edge {
        Vertex destination;
        int weight;

        public Edge(Vertex destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{to=" + destination + ", weight=" + weight + '}';
        }
    }

    public Map<Vertex, Integer> dijkstra(Vertex source) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        PriorityQueue<VertexDistance> pq = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.distance));

        for (Vertex vertex : adjVertices.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.add(new VertexDistance(source, 0));

        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();
            Vertex u = current.vertex;

            for (Edge edge : adjVertices.getOrDefault(u, Collections.emptyList())) {
                Vertex v = edge.destination;
                int newDist = distances.get(u) + edge.weight;
                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    previous.put(v, u);
                    pq.add(new VertexDistance(v, newDist));
                }
            }
        }

        return distances;
    }

    private static class VertexDistance {
        Vertex vertex;
        int distance;

        public VertexDistance(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // demo

    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        WeightedGraph graph = new WeightedGraph();
        graph.adjVertices = new HashMap<>();

        graph.adjVertices.put(a, Arrays.asList(new Edge(b, 1), new Edge(c, 4)));
        graph.adjVertices.put(b, Arrays.asList(new Edge(c, 2), new Edge(d, 5)));
        graph.adjVertices.put(c, Arrays.asList(new Edge(d, 1)));
        graph.adjVertices.put(d, new ArrayList<>());

        Map<Vertex, Integer> distances = graph.dijkstra(a);
        for (Map.Entry<Vertex, Integer> entry : distances.entrySet()) {
            System.out.println("Distance from A to " + entry.getKey().label + " = " + entry.getValue());
        }
    }

}
