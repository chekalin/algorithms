package com.example.graph;

import com.example.graph.structure.WeightedDirectedGraph;
import com.example.graph.structure.WeightedDirectedGraph.WeightedEdge;

import java.util.*;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;

public class ShortestPath {

    public static int findShortestPath(WeightedDirectedGraph graph, int from, int to) {
        Map<Integer, Integer> paths = new HashMap<>();
        paths.put(from, 0);
        List<Integer> processed = newArrayList(from);
        while (processed.size() != graph.getNumberOfNodes()) {
            Optional<WeightedEdge> nextEdgeOptional = findNextEdgeTobeProcessed(graph, processed, paths);
            nextEdgeOptional.ifPresent(edge -> {
                paths.put(edge.to, paths.get(edge.from) + edge.weight);
                processed.add(edge.to);
            });
        }
        return paths.get(to);
    }

    private static Optional<WeightedEdge> findNextEdgeTobeProcessed(WeightedDirectedGraph graph, List<Integer> processed, Map<Integer, Integer> paths) {
        return processed.stream()
                .flatMap(node -> graph
                        .getOutgoingEdges(node).stream()
                        .filter(notYetProcessed(processed)))
                .min(byShortestPath(paths));
    }

    private static Predicate<WeightedEdge> notYetProcessed(List<Integer> processed) {
        return edge -> !processed.contains(edge.to);
    }

    private static Comparator<WeightedEdge> byShortestPath(final Map<Integer, Integer> paths) {
        return (edge1, edge2) -> Integer.compare(
                paths.get(edge1.from) + edge1.weight,
                paths.get(edge2.from) + edge2.weight
        );
    }

}
