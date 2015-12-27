package com.example.graph;

import com.google.common.base.Joiner;

import java.util.*;

public class Graph {
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
            if(next.id == node) {
                for (Integer adjacentNode : getAdjacentNodes(node)) {
                    removeEdge(node, adjacentNode);
                }
                iterator.remove();
            }
        }
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

    private static class Edge {
        private final int node1;
        private final int node2;

        private Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", node1, node2);
        }
    }
}
