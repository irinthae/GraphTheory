package operations;

import graph.Graph;
import operations.algorithms.Kruskal;
import operations.algorithms.Prim;
import operations.dataStructures.Component;
import operations.dataStructures.SpanningTree;

import java.util.ArrayList;
import java.util.List;

public class Trees {

    public static SpanningTree findMST(Graph graph) {
        if (graph.getEdgeList().size() > (graph.getSumVertices() * graph.getSumVertices()) / 2) {
            return Prim.findMST(graph, graph.getVertexList(), graph.getEdgeList());
        }
        return Kruskal.findMST(graph);
    }

    public static List<SpanningTree> findForest(Graph graph, List<Component> componentList) {
        List<SpanningTree> mstList = new ArrayList<>();

        for (Component component : componentList) {
            mstList.add(Prim.findMST(graph, component.getVertexList(), component.getEdgeList()));
        }

        return mstList;
    }


}
