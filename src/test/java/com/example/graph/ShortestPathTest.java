package com.example.graph;

import com.example.graph.structure.WeightedDirectedGraph;
import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.List;

import static com.example.graph.GraphReader.readWeightedDirectedGraphFromFile;
import static com.example.graph.ShortestPath.findShortestPath;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShortestPathTest {

    @Test
    public void shouldReturnZeroForPathToSameNode() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 1, 100);
        int shortestPath = findShortestPath(graph, 1, 1);
        assertThat(shortestPath, is(0));
    }

    @Test
    public void shouldReturnShortestPathForSingleEdge() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        int shortestPath = findShortestPath(graph, 1, 2);
        assertThat(shortestPath, is(10));
    }

    @Test
    public void shouldReturnShortestSumOfWeightsForChain() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        graph.addEdge(2, 3, 10);
        int shortestPath = findShortestPath(graph, 1, 3);
        assertThat(shortestPath, is(20));
    }

    @Test
    public void shouldShortestOutOfTwo() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 20);
        graph.addEdge(1, 2, 15);
        int shortestPath = findShortestPath(graph, 1, 2);
        assertThat(shortestPath, is(15));
    }

    @Test
    public void shouldShortestWhenThreeNodes() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 10);
        graph.addEdge(1, 3, 15);
        int shortestPath = findShortestPath(graph, 1, 3);
        assertThat(shortestPath, is(15));
    }

    @Test
    public void testGraph1() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph1.txt");
        assertThat(findShortestPath(graph, 1, 1), is(0));
        assertThat(findShortestPath(graph, 1, 2), is(3));
        assertThat(findShortestPath(graph, 1, 3), is(3));
        assertThat(findShortestPath(graph, 1, 4), is(5));
    }

    @Test
    public void testGraph2() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph2.txt");
        assertThat(findShortestPath(graph, 1, 1), is(0));
        assertThat(findShortestPath(graph, 1, 2), is(3));
        assertThat(findShortestPath(graph, 1, 3), is(4));
        assertThat(findShortestPath(graph, 1, 4), is(5));
    }

    @Test
    public void testGraph3() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph3.txt");
        assertThat(findShortestPath(graph, 1, 4), is(2));
    }

    @Test
    public void testGraph4() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph4.txt");
        assertThat(findShortestPath(graph, 1, 7), is(5));
    }

    @Test
    public void testGraph5() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph5.txt");
        assertThat(findShortestPath(graph, 13, 5), is(26));
    }

    @Test
    public void testGraph6() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph6.txt");
        assertThat(findShortestPath(graph, 28, 6), is(9));
    }

    @Test
    public void testHomework5() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/dijkstraData.txt");
        List<Integer> destinations = newArrayList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);
        List<Integer> results = newArrayList();
        for (int destination : destinations) {
            results.add(findShortestPath(graph, 1, destination));
        }
        System.out.println(Joiner.on(",").join(results));
    }
}