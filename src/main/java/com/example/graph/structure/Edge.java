package com.example.graph.structure;

public class Edge {
    public final int node1;
    public final int node2;

    Edge(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", node1, node2);
    }
}
