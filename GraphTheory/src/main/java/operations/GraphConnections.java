package operations;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import operations.dataStructures.Component;
import operations.dataStructures.ConnectedComponents;
import operations.dataStructures.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class GraphConnections {

    public static List<Component> getConnectedComponents(Graph graph) {
        return new ConnectedComponents(graph).getComponentList();
    }

    public static List<Vertex> getArticulations(Graph graph) {
        int sumComponents = getNumberOfComponents(graph);
        ArrayList<Vertex> vertexList = graph.getVertexList();
        List<Vertex> articulationList = vertexList.stream()
                                                    .filter(vertex -> checkArticulation(vertex, graph, sumComponents))
                                                    .toList();
        updateIndex(vertexList);

        return articulationList;
    }


    private static boolean checkArticulation(Vertex v, Graph graph, int sumComponents) {
        ArrayList<Vertex> vertexList = graph.getVertexList();
        ArrayList<Edge> edgeList = graph.getEdgeList();

        edgeList.removeIf(edge -> edge.contains(v));
        vertexList.remove(v);
        updateIndex(vertexList);

        Graph newGraph = new Graph(vertexList, edgeList);

        return getNumberOfComponents(newGraph) > sumComponents;
    }

    private static void updateIndex(ArrayList<Vertex> vertexList) {
        vertexList.forEach(vertex -> vertex.setIndex(vertexList.indexOf(vertex)));
    }

    public static List<Edge> getBridges(Graph graph) {
        int sumComponents = getNumberOfComponents(graph);
        ArrayList<Edge> edgeList = graph.getEdgeList();
        return edgeList.stream()
                        .filter(edge -> checkBridge(edge, graph, sumComponents))
                        .toList();
    }

    private static boolean checkBridge(Edge edge, Graph graph, int sumComponents) {
        ArrayList<Vertex> vertexList = graph.getVertexList();
        ArrayList<Edge> edgeList = graph.getEdgeList();
        edgeList.remove(edge);

        Graph newGraph = new Graph(vertexList, edgeList);

        return getNumberOfComponents(newGraph) > sumComponents;
    }

    private static int getNumberOfComponents(Graph graph) {
        UnionFind unionFind = new UnionFind(graph);
        ArrayList<Edge> edgeList = graph.getEdgeList();
        for (Edge edge : edgeList) {
            unionFind.unify(edge.getVertex1(), edge.getVertex2());
        }
        return unionFind.getNumComponents();
    }
}
