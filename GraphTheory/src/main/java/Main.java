import formatter.ListFormatter;
import formatter.MatrixFormatter;
import graph.Graph;
import importFile.CsvReader;
import operations.GraphConnections;
import operations.GraphProperties;
import operations.Trees;
import operations.dataStructures.Component;
import operations.dataStructures.SpanningTree;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(args[0]);
        csvReader.readFile();
        Graph graph = new Graph(csvReader.getAdjacencyMatrix(), csvReader.getVertexList());
        List<Component> connectedComponentsList = GraphConnections.getConnectedComponents(graph);
        boolean graphIsConnected = connectedComponentsList.size() == 1;

        System.out.println("\n############## GRAPH ##############\n");
        System.out.println(graph);

        System.out.println("\n############## PROPERTIES ##############\n");

        System.out.println("DISTANCE MATRIX:\n");
        int[][] distanceMatrix = GraphProperties.getDistanceMatrix(graph);
        System.out.println(new MatrixFormatter(graph, distanceMatrix).getFormattedMatrix());

        System.out.println("ECCENTRICITIES:");
        if (!graphIsConnected) {
            graph.getVertexList().forEach(vertex -> System.out.print(vertex.getLabel() + "=∞ "));
            System.out.println("\n");
        } else {
            graph.getVertexList().forEach(vertex ->
                                            System.out.print(vertex.getLabel() + "="
                                                    + GraphProperties.getEccentricity(graph, vertex)+" "));
            System.out.println("\n");
        }

        System.out.println("RADIUS:");
        System.out.println(!graphIsConnected ? " ∞ \n" : GraphProperties.getRadius(graph) + "\n");

        System.out.println("DIAMETER:");
        System.out.println(!graphIsConnected ? " ∞ \n" : GraphProperties.getDiameter(graph) + "\n");

        System.out.println("CENTER:");
        System.out.println(!graphIsConnected ?
                            new ListFormatter<>(graph.getVertexList()) + "\n" :
                            new ListFormatter<>(GraphProperties.getCenter(graph)) + "\n");


        System.out.println("\n############## CONNECTION ##############\n");

        System.out.println("CONNECTED COMPONENTS: ");
        connectedComponentsList.forEach(System.out::println);
        System.out.println();

        System.out.println("ARTICULATIONS: ");
        System.out.println(new ListFormatter<>(GraphConnections.getArticulations(graph)));
        System.out.println();

        System.out.println("BRIDGES: ");
        System.out.println(new ListFormatter<>(GraphConnections.getBridges(graph)));
        System.out.println();


        if (graphIsConnected) {
            System.out.println("\n############## MINIMUM SPANNING TREE ##############\n");
            System.out.println(Trees.findMST(graph));
        } else {
            System.out.println("\n############## FOREST ##############\n");
            List<SpanningTree> forest = Trees.findForest(graph, connectedComponentsList);
            for (SpanningTree mst : forest) {
                System.out.println("Component" + (forest.indexOf(mst) + 1) + " Minimum Spanning Tree:");
                System.out.println(mst + "\n");
            }
        }
    }

}