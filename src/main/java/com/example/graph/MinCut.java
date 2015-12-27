package com.example.graph;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MinCut {

    static void contract(Graph graph, int node1, int node2) {
        graph.getEdgesForNode(node2).stream()
                .filter(doesNotPointToNode(node1))
                .forEach(createEdgesToNode1(graph, node1, node2));
        graph.removeNode(node2);
    }

    private static Consumer<Graph.Edge> createEdgesToNode1(Graph graph, int node1, int node2) {
        return edge -> {
            if (edge.node1 == node2) {
                graph.addEdge(node1, edge.node2);
            } else {
                graph.addEdge(edge.node1, node1);
            }
        };
    }

    private static Predicate<Graph.Edge> doesNotPointToNode(int node1) {
        return edge1 -> edge1.node1 != node1 && edge1.node2 != node1;
    }

    public static int findMinCut(Graph graph) {
        Graph graphCopy = graph.copy();
        while (graphCopy.getNumberOfNodes() > 2) {
            Graph.Edge randomEdge = graphCopy.getRandomEdge();
            contract(graphCopy, randomEdge.node1, randomEdge.node2);
        }
        return graphCopy.getNumberOfEdges();
    }

    public static int findMinCut(Graph graph, int retries) {
        int minCut = findMinCut(graph);
        for (int i = 0; i < retries; i++) {
            int cut = findMinCut(graph);
            if (cut < minCut) minCut = cut;
        }
        return minCut;
    }
}
