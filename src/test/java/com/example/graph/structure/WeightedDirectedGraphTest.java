package com.example.graph.structure;

import com.example.graph.structure.WeightedDirectedGraph.WeightedEdge;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class WeightedDirectedGraphTest {

    @Test
    public void shouldInitializeEmpty() throws Exception {
        assertThat(new WeightedDirectedGraph().getNumberOfNodes(), is(0));
    }

    @Test
    public void shouldAddEdges() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        assertThat(graph.getNumberOfEdges(), is(1));
    }

    @Test
    public void shouldInitializeNodeWhenAddingEdge() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        assertThat(graph.getNumberOfNodes(), is(2));
    }

    @Test
    public void shouldInitializeNodeOnlyOnce() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 10);
        assertThat(graph.getNumberOfNodes(), is(3));
    }

    @Test
    public void shouldReturnOutgoingEdges() throws Exception {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        graph.addEdge(1, 2, 10);
        assertThat(graph.getOutgoingEdges(1), hasSize(1));
        assertThat(graph.getOutgoingEdges(1), hasItem(new WeightedEdge(1, 2, 10)));
    }
}