package operations.dataStructures;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponents {
    private final Graph graph;
    private final ArrayList<Component> componentList;
    private final ArrayList<Integer> visited;

    public ConnectedComponents(Graph graph) {
        this.graph = graph;
        this.visited = new ArrayList<>();
        this.componentList = new ArrayList<>();
        setComponentList();
    }

    public ArrayList<Component> getComponentList() {
        return componentList;
    }

    private void setComponentList() {
        ArrayList<Vertex> vertexList = graph.getVertexList();

        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited.contains(i)) {
                componentList.add(findComponent(vertexList.get(i)));
            }
        }
    }

    private Component findComponent(Vertex startVertex) {
        Queue<Vertex> queue = new LinkedList<>();
        Component component = new Component(componentList.size());

        discoverStartVertex(queue, startVertex, component);

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            addEdgesToComponent(v, component);
            discoverNeighbours(queue, v, component);
        }

        return component;
    }

    private void discoverStartVertex(Queue<Vertex> queue, Vertex startVertex, Component component) {
        queue.offer(startVertex);
        visited.add(startVertex.getIndex());
        component.addVertex(startVertex);
        addEdgesToComponent(startVertex, component);
    }

    private void discoverNeighbours(Queue<Vertex> queue, Vertex v, Component component) {
        ArrayList<Vertex> neighbours = graph.getNeighbourList(v);

        for (Vertex n : neighbours) {
            if (!visited.contains(n.getIndex())) {
                queue.add(n);
                visited.add(n.getIndex());
                component.addVertex(n);;
            }
        }
    }

    private void addEdgesToComponent(Vertex v, Component component) {
        for (Edge edge : graph.getEdgeList()) {
            if (edge.contains(v) && !component.getEdgeList().contains(edge)) {
                component.addEdge(edge);
            }
        }
    }

}
