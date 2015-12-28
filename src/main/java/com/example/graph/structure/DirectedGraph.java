package com.example.graph.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class DirectedGraph {

    private List<Edge> edges = newArrayList();
    private Map<Integer, NodeWithDirectedEdges> nodes = newHashMap();

    public DirectedGraph(int numberOfNodes) {
        for (int i = 1; i <= numberOfNodes; i++) {
            nodes.put(i, new NodeWithDirectedEdges());
        }
    }

    public Collection<Edge> getOutgoingEdges(int nodeId) {
        return nodes.get(nodeId).outgoingEdges;
    }

    public Collection<Edge> getIncomingEdges(int nodeId) {
        return nodes.get(nodeId).incomingEdges;
    }

    public void addEdge(int from, int to) {
        Edge edge = new Edge(from, to);
        edges.add(edge);
        nodes.get(from).outgoingEdges.add(edge);
        nodes.get(to).incomingEdges.add(edge);
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    private class NodeWithDirectedEdges {
        private List<Edge> incomingEdges;
        private List<Edge> outgoingEdges;

        private NodeWithDirectedEdges() {
            incomingEdges = new ArrayList<>();
            outgoingEdges = new ArrayList<>();
        }
    }
}
