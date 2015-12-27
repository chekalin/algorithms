package com.example.graph;

import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNot.not;

public class GraphTest {

    @Test
    public void shouldBeCreatedWithEmptyEdgesAndNodes() throws Exception {
        Graph graph = new Graph();
        assertThat(graph.getNumberOfNodes(), is(0));
        assertThat(graph.getNumberOfEdges(), is(0));
    }

    @Test
    public void shouldAddNode() throws Exception {
        Graph graph = new Graph();
        graph.addNode(1);
        assertThat(graph.getNumberOfNodes(), is(1));
    }

    @Test
    public void shouldAddNodes() throws Exception {
        Graph graph = new Graph();
        graph.addNodes(1, 2);
        assertThat(graph.getNumberOfNodes(), is(2));
    }

    @Test
    public void shouldAddEdges() throws Exception {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        assertThat(graph.getNumberOfEdges(), is(1));
    }

    @Test
    public void shouldRemoveEdges() throws Exception {
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.removeEdge(1, 2);
        assertThat(graph.getNumberOfEdges(), is(1));
        assertThat(graph.getAdjacentNodes(1), is(empty()));
    }

    @Test
    public void shouldRemoveEdgesEvenWhenSpecifiedInReverseOrder() throws Exception {
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.removeEdge(2, 1);
        assertThat(graph.getNumberOfEdges(), is(1));
        assertThat(graph.getAdjacentNodes(1), is(empty()));
    }

    @Test
    public void shouldRemoveNodeWithAllEdges() throws Exception {
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.removeNode(1);
        assertThat(graph.getNumberOfNodes(), is(2));
        assertThat(graph.getAdjacentNodes(2), hasSize(1));
        assertThat(graph.getAdjacentNodes(2), not(hasItem(1)));
    }

    @Test
    public void shouldReturnZeroAdjacentNodesWhenOneNode() throws Exception {
        Graph graph = new Graph();
        graph.addNode(1);
        assertThat(graph.getAdjacentNodes(1), is(empty()));
    }

    @Test
    public void shouldReturnAdjacentNodeWhenThereIsAnEdge() throws Exception {
        Graph graph = new Graph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);
        Collection<Integer> nodesAdjacentTo1 = graph.getAdjacentNodes(1);
        assertThat(nodesAdjacentTo1, hasSize(1));
        assertThat(nodesAdjacentTo1, hasItem(2));
        Collection<Integer> nodesAdjacentTo2 = graph.getAdjacentNodes(2);
        assertThat(nodesAdjacentTo2, hasSize(1));
        assertThat(nodesAdjacentTo2, hasItem(1));
    }

    @Test
    public void shouldReturnMultipleAdjacentNodes() throws Exception {
        Graph graph = new Graph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        Collection<Integer> nodesAdjacentTo1 = graph.getAdjacentNodes(1);
        assertThat(nodesAdjacentTo1, hasSize(2));
        assertThat(nodesAdjacentTo1, hasItems(2, 3));
    }
}