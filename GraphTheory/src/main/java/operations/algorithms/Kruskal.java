package operations.algorithms;

import graph.Edge;
import graph.Graph;
import operations.dataStructures.SpanningTree;
import operations.dataStructures.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    public static SpanningTree findMST(Graph graph) {
        int sum = 0;
        int numberOfEdgesInMST = graph.getSumVertices() - 1;
        UnionFind unionFind = new UnionFind(graph);
        List<Edge> mstEdgeList = new ArrayList<>();
        List<Edge> originalEdgeList = graph.getEdgeList();
        Collections.sort(originalEdgeList);

        for (Edge edge : originalEdgeList) {
            if (unionFind.isConnected(edge.getVertex1(), edge.getVertex2())) {
                continue;
            }

            unionFind.unify(edge.getVertex1(), edge.getVertex2());
            mstEdgeList.add(edge);
            sum += edge.getWeight();
        }

        return mstEdgeList.size() == numberOfEdgesInMST ? new SpanningTree(mstEdgeList, sum) : null;
    }
}
