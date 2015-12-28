package com.example.graph;

import com.example.graph.structure.DirectedGraph;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static com.example.graph.GraphReader.readDirectedGraphFromFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StronglyConnectedComponentCalculatorTest {

    private StronglyConnectedComponentCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StronglyConnectedComponentCalculator();
    }

    @Test
    public void shouldReturnOneForGraphWithOneNode() throws Exception {
        DirectedGraph graph = new DirectedGraph(1);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{1, 0, 0, 0, 0}));
    }

    @Test
    public void shouldReturnOneOneForPathOfTwoNodes() throws Exception {
        DirectedGraph graph = new DirectedGraph(2);
        graph.addEdge(1, 2);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{1, 1, 0, 0, 0}));
    }

    @Test
    public void shouldReturnFourFour() throws Exception {
        /*
        1->2->5->6
        ^  |  ^  |
        |  V  |  V
        3<-4  7<-8
         */
        DirectedGraph graph = new DirectedGraph(8);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 1);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 8);
        graph.addEdge(8, 7);
        graph.addEdge(7, 5);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{4, 4, 0, 0, 0}));
    }

    @Test
    public void reverseDfsShouldSaveFinishingTimesPerNode() throws Exception {
        // Original: 1 -> 2 -> 3
        // Reversed: 1 <- 2 <- 3
        // f(1) = 1, f(2) = 2, f(3) = 3
        DirectedGraph graph = new DirectedGraph(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        calculator.reverseDfs(graph);
        assertThat(calculator.finishingTimes.get(1), is(1));
        assertThat(calculator.finishingTimes.get(2), is(2));
        assertThat(calculator.finishingTimes.get(3), is(3));
    }

    @Test
    public void testGraph1() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/testGraph1.txt", 9);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{3, 3, 3, 0, 0}));
    }

    @Test
    public void testGraph2() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/testGraph2.txt", 8);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{3, 3, 2, 0, 0}));
    }

    @Test
    public void testGraph3() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/testGraph3.txt", 8);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{3, 3, 1, 1, 0}));
    }

    @Test
    public void testGraph4() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/testGraph4.txt", 8);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{7, 1, 0, 0, 0}));
    }

    @Test
    public void testGraph5() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/testGraph5.txt", 12);
        int[] result = calculator.calculate(graph);
        assertThat(result, is(new int[]{6, 3, 2, 1, 0}));
    }

    @Test
    @Ignore("requires homework4/SCC.txt")
    public void testHomework4() throws Exception {
        DirectedGraph graph = readDirectedGraphFromFile("homework4/SCC.txt", 875714);
        int[] result = calculator.calculate(graph);
        System.out.println("result = " + Arrays.toString(result));
    }
}