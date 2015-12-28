package com.example.graph;

import org.junit.Ignore;
import org.junit.Test;

import static com.example.graph.GraphReader.readGraphFromFile;
import static com.example.graph.MinCut.contract;
import static com.example.graph.MinCut.findMinCut;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class MinCutTest {

    @Test
    public void shouldContractTwoNodesIntoOne() throws Exception {
        // 1 - 2 -> 1 | contracting 1, 2
        Graph graph = new Graph();
        graph.addNodes(1, 2);
        graph.addEdge(1, 2);
        contract(graph, 1, 2);
        assertThat(graph.getNumberOfNodes(), is(1));
    }

    @Test
    public void shouldContractTwoNodesOutOfThree() throws Exception {
        // 1 - 2 - 3 -> 1 - 3 | contracting 1, 2
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        contract(graph, 1, 2);
        assertThat(graph.getNumberOfNodes(), is(2));
        assertThat(graph.getNumberOfEdges(), is(1));
        assertThat(graph.getAdjacentNodes(1), hasSize(1));
        assertThat(graph.getAdjacentNodes(1), hasItem(3));
        assertThat(graph.getAdjacentNodes(3), hasSize(1));
        assertThat(graph.getAdjacentNodes(3), hasItem(1));
    }

    @Test
    public void shouldContractTwoNodesOutOfFour() throws Exception {
        /*
            1 - 2        2
                       /
            |   | -> 1   | | contracting 1, 4
                       \
            4 - 3        3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        contract(graph, 1, 4);
        assertThat(graph.getNumberOfNodes(), is(3));
        assertThat(graph.getNumberOfNodes(), is(3));
        assertThat(graph.getAdjacentNodes(1), hasSize(2));
        assertThat(graph.getAdjacentNodes(1), hasItems(2, 3));
        assertThat(graph.getAdjacentNodes(2), hasSize(2));
        assertThat(graph.getAdjacentNodes(2), hasItems(1, 3));
        assertThat(graph.getAdjacentNodes(3), hasSize(2));
        assertThat(graph.getAdjacentNodes(3), hasItems(1, 2));
    }

    @Test
    public void shouldLeaveParallelEdges() throws Exception {
        /*
                2
              /
            1   | -> 1 = 2 | contracting 2, 3
              \
                3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        contract(graph, 2, 3);
        assertThat(graph.getNumberOfEdges(), is(2));
    }

    @Test
    public void shouldAddParallelEdges() throws Exception {
        /*
                 2
              //         -
            1    | -> 1  =  2 | contracting 2, 3
              \\         -
                 3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 1);
        contract(graph, 2, 3);
        assertThat(graph.getNumberOfEdges(), is(4));
    }

    @Test
    public void shouldReturnNumberOfEdgesForGraphWithTwoNodes() throws Exception {
        // 1 - 2
        Graph graph = new Graph();
        graph.addNodes(1, 2);
        graph.addEdge(1, 2);
        assertThat(findMinCut(graph), is(1));
        // 1 = 2
        graph.addEdge(1, 2);
        assertThat(findMinCut(graph), is(2));
    }

    @Test
    public void shouldReturnTwoForCircularGraphOfThreeNodes() throws Exception {
        /*
                2
              /
            1   |
              \
                3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        assertThat(findMinCut(graph), is(2));
    }

    @Test
    public void shouldReturnTwoForCircularGraphOfFourNodes() throws Exception {
        /*
            1 - 2

            |   |

            4 - 3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        assertThat(findMinCut(graph), is(2));
    }

    @Test
    public void shouldReturnThreeForGraphOfFourNodesWithAllNodesConnected() throws Exception {
        /*
            1 - 2

            | X |

            4 - 3
         */
        Graph graph = new Graph();
        graph.addNodes(1, 2, 3, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        assertThat(findMinCut(graph, 10), is(3));
    }

    @Test
    public void testGraph1() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph1.txt");
        int minCut = findMinCut(graph, 1000);
        assertThat(minCut, is(2));
    }

    @Test
    public void testGraph2() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph2.txt");
        int minCut = findMinCut(graph, 1000);
        assertThat(minCut, is(2));
    }

    @Test
    public void testGraph3() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph3.txt");
        int minCut = findMinCut(graph, 1000);
        assertThat(minCut, is(1));
    }

    @Test
    public void testGraph4() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph4.txt");
        int minCut = findMinCut(graph, 1000);
        assertThat(minCut, is(3));
    }

    @Test
    public void testGraph5() throws Exception {
        Graph graph = readGraphFromFile("homework3/testGraph5.txt");
        int minCut = findMinCut(graph, 1000);
        assertThat(minCut, is(2));
    }

    @Test
    @Ignore("slow")
    public void testHomework3() throws Exception {
        Graph graph = readGraphFromFile("homework3/kargerMinCut.txt");
        int minCut = findMinCut(graph, 500);
        System.out.println("minCut = " + minCut);
    }

}