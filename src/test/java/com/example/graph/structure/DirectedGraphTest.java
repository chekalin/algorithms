package com.example.graph.structure;

import com.example.graph.structure.DirectedGraph;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class DirectedGraphTest {

    @Test
    public void shouldBeCreatedWithNodesAccordingToSize() throws Exception {
        assertThat(new DirectedGraph(1).getNumberOfNodes(), is(1));
        assertThat(new DirectedGraph(2).getNumberOfNodes(), is(2));
        assertThat(new DirectedGraph(3).getNumberOfNodes(), is(3));
    }

    @Test
    public void shouldAddEdges() throws Exception {
        DirectedGraph graph = new DirectedGraph(2);
        graph.addEdge(1, 2);
        assertThat(graph.getNumberOfEdges(), is(1));
        assertThat(graph.getOutgoingEdges(1), hasSize(1));
        assertThat(graph.getIncomingEdges(2), hasSize(1));
    }

    @Test
    public void shouldReturnOnlyOutgoingEdges() throws Exception {
        // 1 <-> 2
        DirectedGraph graph = new DirectedGraph(2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        assertThat(graph.getOutgoingEdges(1), hasSize(1));
    }
}