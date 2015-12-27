package com.example.graph;

import org.junit.Ignore;
import org.junit.Test;

import static com.example.graph.GraphReader.readGraphFromFile;

public class MinCutTest {

    @Test
    @Ignore
    public void testGraph1() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph1.txt");
        System.out.println(graph);
    }

    @Test
    @Ignore
    public void testHomework3() throws Exception {
        Graph graph = readGraphFromFile("homework3/kargerMinCut.txt");
        System.out.println(graph);
    }

}