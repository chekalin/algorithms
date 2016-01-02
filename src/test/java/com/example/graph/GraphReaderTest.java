package com.example.graph;

import com.example.graph.structure.DirectedGraph;
import com.example.graph.structure.Edge;
import com.example.graph.structure.UndirectedGraph;
import com.example.graph.structure.WeightedDirectedGraph;
import com.example.graph.structure.WeightedDirectedGraph.WeightedEdge;
import org.junit.Test;

import static com.example.graph.GraphReader.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class GraphReaderTest {

    @Test
    public void shouldReadGraphFromFile() throws Exception {
        UndirectedGraph graph = readGraphFromFile("homework3/simple.txt");
        assertThat(graph.getNumberOfNodes(), is(3));
        assertThat(graph.getNumberOfEdges(), is(2));
        assertThat(graph.getAdjacentNodes(1), hasSize(2));
        assertThat(graph.getAdjacentNodes(1), hasItems(2, 3));
        assertThat(graph.getAdjacentNodes(2), hasSize(1));
        assertThat(graph.getAdjacentNodes(2), hasItem(1));
        assertThat(graph.getAdjacentNodes(3), hasSize(1));
        assertThat(graph.getAdjacentNodes(3), hasItem(1));
    }

    @Test
    public void shouldReadDirectedGraphFromFile() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/simple.txt", 4);
        assertThat(graph.getNumberOfNodes(), is(4));
        assertThat(graph.getNumberOfEdges(), is(4));

        assertThat(graph.getOutgoingEdges(1), hasSize(1));
        Edge edgeFromNode1 = graph.getOutgoingEdges(1).iterator().next();
        assertThat(edgeFromNode1.node1, is(1));
        assertThat(edgeFromNode1.node2, is(2));

        assertThat(graph.getOutgoingEdges(2), hasSize(1));
        Edge edgeFromNode2 = graph.getOutgoingEdges(2).iterator().next();
        assertThat(edgeFromNode2.node1, is(2));
        assertThat(edgeFromNode2.node2, is(3));
    }

    @Test
    public void shouldReadWeightedDirectedGraphFromFile() throws Exception {
        WeightedDirectedGraph graph = readWeightedDirectedGraphFromFile("homework5/testGraph1.txt");

        assertThat(graph.getNumberOfNodes(), is(4));
        assertThat(graph.getNumberOfEdges(), is(5));
        assertThat(graph.getOutgoingEdges(1), hasSize(2));
        assertThat(graph.getOutgoingEdges(1), hasItems(new WeightedEdge(1, 2, 3), new WeightedEdge(1, 3, 3)));
        assertThat(graph.getOutgoingEdges(2), hasSize(2));
        assertThat(graph.getOutgoingEdges(2), hasItems(new WeightedEdge(2, 3, 1), new WeightedEdge(2, 4, 2)));
        assertThat(graph.getOutgoingEdges(3), hasSize(1));
        assertThat(graph.getOutgoingEdges(3), hasItems(new WeightedEdge(3, 4, 50)));
        assertThat(graph.getOutgoingEdges(4), hasSize(0));
    }

}