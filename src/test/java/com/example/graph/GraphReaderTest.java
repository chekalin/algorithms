package com.example.graph;

import org.junit.Test;

import static com.example.graph.GraphReader.readGraphFromFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class GraphReaderTest {

    @Test
    public void shouldReadGraphFromFile() throws Exception {
        Graph graph = readGraphFromFile("homework3/simple.txt");
        assertThat(graph.getNumberOfNodes(), is(3));
        assertThat(graph.getNumberOfEdges(), is(2));
        assertThat(graph.getAdjacentNodes(1), hasSize(2));
        assertThat(graph.getAdjacentNodes(1), hasItems(2, 3));
        assertThat(graph.getAdjacentNodes(2), hasSize(1));
        assertThat(graph.getAdjacentNodes(2), hasItem(1));
        assertThat(graph.getAdjacentNodes(3), hasSize(1));
        assertThat(graph.getAdjacentNodes(3), hasItem(1));
        System.out.println(graph);
    }
}