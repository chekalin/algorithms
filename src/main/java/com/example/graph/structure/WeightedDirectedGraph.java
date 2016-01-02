package com.example.graph.structure;

import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class WeightedDirectedGraph {

    List<WeightedEdge> edges = newArrayList();
    Map<Integer, List<WeightedEdge>> nodes = newHashMap();

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    public Collection<WeightedEdge> getOutgoingEdges(int node) {
        return newArrayList(nodes.get(node));
    }

    public void addEdge(int from, int to, int weight) {
        WeightedEdge edge = new WeightedEdge(from, to, weight);
        edges.add(edge);
        List<WeightedEdge> outgoingEdges = nodes.get(from);
        if (outgoingEdges == null) {
            nodes.put(from, newArrayList(edge));
        } else {
            outgoingEdges.add(edge);
        }
        if (nodes.get(to) == null) {
            nodes.put(to, newArrayList());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<WeightedEdge>> node : nodes.entrySet()) {
            sb.append(node.getKey()).append(": ");
            sb.append(Joiner.on(",").join(node.getValue()));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static class WeightedEdge {
        public final int from;
        public final int to;
        public final int weight;

        public WeightedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WeightedEdge that = (WeightedEdge) o;

            if (from != that.from) return false;
            if (to != that.to) return false;
            return weight == that.weight;

        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            result = 31 * result + weight;
            return result;
        }

        @Override
        public String toString() {
            return "{" + from +
                    " -> " + to +
                    ", " + weight +
                    '}';
        }
    }

}
