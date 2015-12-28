package com.example.graph;

import com.example.graph.structure.DirectedGraph;
import com.example.graph.structure.Edge;

import java.util.Collection;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class StronglyConnectedComponentCalculator {

    Map<Integer, Integer> finishingTimes;
    boolean[] explored;
    int currentFinishingTime;
    int[] sccSizeByLeader;
    int currentLeader;

    public int[] calculate(DirectedGraph graph) {
        int[] result = new int[5];
        reverseDfs(graph);
        dfs(graph);
        for (int i = 0; i < 5; i++) {
            int leaderNode = findIndexOfLeaderWithMaxSize();
            result[i] = sccSizeByLeader[leaderNode];
            sccSizeByLeader[leaderNode] = 0;
        }
        return result;
    }

    void reverseDfs(DirectedGraph graph) {
        finishingTimes = newHashMap();
        explored = new boolean[graph.getNumberOfNodes()];
        currentFinishingTime = 0;
        for (int i = graph.getNumberOfNodes(); i > 0; i--) {
            if (!explored[i - 1]) {
                reverseDfs(graph, i);
            }
        }
    }

    private void reverseDfs(DirectedGraph graph, int node) {
        explored[node - 1] = true;
        Collection<Edge> edges = graph.getIncomingEdges(node);
        for (Edge edge : edges) {
            if (!explored[edge.node1 - 1]) {
                reverseDfs(graph, edge.node1);
            }
        }
        currentFinishingTime++;
        finishingTimes.put(currentFinishingTime, node);
    }

    private void dfs(DirectedGraph graph) {
        explored = new boolean[graph.getNumberOfNodes()];
        sccSizeByLeader = new int[graph.getNumberOfNodes()];
        for (int i = graph.getNumberOfNodes(); i > 0; i--) {
            int node = finishingTimes.get(i);
            currentLeader = node;
            if (!explored[node - 1]) {
                dfs(graph, node);
            }
        }
    }

    private void dfs(DirectedGraph graph, int node) {
        explored[node - 1] = true;
        Collection<Edge> edges = graph.getOutgoingEdges(node);
        for (Edge edge : edges) {
            if (!explored[edge.node2 - 1]) {
                dfs(graph, edge.node2);
            }
        }
        sccSizeByLeader[currentLeader - 1]++;
    }

    private int findIndexOfLeaderWithMaxSize() {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < sccSizeByLeader.length; i++) {
            int size = sccSizeByLeader[i];
            if (size > max) {
                max = size;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
