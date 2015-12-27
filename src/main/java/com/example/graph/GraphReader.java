package com.example.graph;

import com.google.common.base.Splitter;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class GraphReader {

    public static Graph readGraphFromFile(String filename) throws IOException {
        Graph graph = new Graph();
        List<String> nodeLines = Resources.readLines(Resources.getResource(filename), Charset.forName("UTF-8"));
        for (String nodeLine : nodeLines) {
            Iterable<String> nodeTokens = Splitter.on(Pattern.compile("\t|\\s+")).split(nodeLine);
            Iterator<String> iterator = nodeTokens.iterator();
            int nodeId = Integer.parseInt(iterator.next());
            graph.addNode(nodeId);
            while(iterator.hasNext()) {
                String next = iterator.next();
                if (next.isEmpty()) continue;
                int edgeTo = Integer.parseInt(next);
                // assume input is sorted to avoid duplicate edges
                if (edgeTo > nodeId) {
                    graph.addEdge(nodeId, edgeTo);
                }
            }
        }
        return graph;
    }

}
