package com.example.graph;

import com.example.graph.structure.Edge;
import com.example.graph.structure.UndirectedGraph;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MinCut {

    static void contract(UndirectedGraph graph, int node1, int node2) {
        graph.getEdgesForNode(node2).stream()
                .filter(doesNotPointToNode(node1))
                .forEach(createEdgesToNode1(graph, node1, node2));
        graph.removeNode(node2);
    }

    private static Consumer<Edge> createEdgesToNode1(UndirectedGraph graph, int node1, int node2) {
        return edge -> {
            if (edge.node1 == node2) {
                graph.addEdge(node1, edge.node2);
            } else {
                graph.addEdge(edge.node1, node1);
            }
        };
    }

    private static Predicate<Edge> doesNotPointToNode(int node1) {
        return edge1 -> edge1.node1 != node1 && edge1.node2 != node1;
    }

    public static int findMinCut(UndirectedGraph graph) {
        UndirectedGraph graphCopy = graph.copy();
        while (graphCopy.getNumberOfNodes() > 2) {
            Edge randomEdge = graphCopy.getRandomEdge();
            contract(graphCopy, randomEdge.node1, randomEdge.node2);
        }
        return graphCopy.getNumberOfEdges();
    }

    public static int findMinCut(UndirectedGraph graph, int retries) {
        int minCut = findMinCut(graph);
        for (int i = 0; i < retries; i++) {
            int cut = findMinCut(graph);
            if (cut < minCut) minCut = cut;
        }
        return minCut;
    }
}
