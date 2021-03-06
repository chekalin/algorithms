package com.example.graph.structure;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

public class UndirectedGraph {

    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void addNode(int id) {
        nodes.add(new Node(id));
    }

    public void addEdge(int node1, int node2) {
        edges.add(new Edge(node1, node2));
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    public Collection<Integer> getAdjacentNodes(int nodeId) {
        Set<Integer> result = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.node1 == nodeId) {
                result.add(edge.node2);
            } else if (edge.node2 == nodeId) {
                result.add(edge.node1);
            }
        }
        return result;
    }

    public Collection<Edge> getEdgesForNode(int node) {
        return newArrayList(filter(this.edges, edge -> edge.node1 == node || edge.node2 == node));
    }

    public void removeEdge(int node1, int node2) {
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge next = iterator.next();
            if ((next.node1 == node1 && next.node2 == node2)
                    || (next.node1 == node2 && next.node2 == node1)) {
                iterator.remove();
            }
        }
    }

    public void addNodes(int... nodes) {
        for (int node : nodes) {
            addNode(node);
        }
    }

    public void removeNode(int node) {
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.id == node) {
                for (Integer adjacentNode : getAdjacentNodes(node)) {
                    removeEdge(node, adjacentNode);
                }
                iterator.remove();
            }
        }
    }

    public Edge getRandomEdge() {
        return edges.get(new Random().nextInt(edges.size()));
    }

    public UndirectedGraph copy() {
        UndirectedGraph copy = new UndirectedGraph();
        copy.edges = Lists.newArrayList(edges);
        copy.nodes = newArrayList(nodes);
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes(").append(getNumberOfNodes()).append("):\n");
        for (Node node : nodes) {
            sb.append(node.id)
                    .append(" - ")
                    .append(Joiner.on(" ").join(getAdjacentNodes(node.id)))
                    .append("\n");
        }
        sb.append("Edges(").append(getNumberOfEdges()).append("):\n");
        sb.append(Joiner.on(", ").join(edges));
        sb.append("\n");
        return sb.toString();
    }

    private static class Node {

        private final int id;

        private Node(int id) {
            this.id = id;
        }
    }

}
