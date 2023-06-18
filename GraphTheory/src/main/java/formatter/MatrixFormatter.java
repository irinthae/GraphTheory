package formatter;

import graph.Graph;

public class MatrixFormatter {
    private Graph graph;
    private int[][] matrix;
    private String formattedMatrix;

    public MatrixFormatter(Graph graph) {
        this.graph = graph;
        this.matrix = graph.getAdjacencyMatrix();
        setFormattedMatrix();
    }

    public MatrixFormatter(Graph graph, int[][] matrix) {
        this.graph = graph;
        this.matrix = matrix;
        setFormattedMatrix();
    }

    public String getFormattedMatrix() {
        return this.formattedMatrix;
    }

    private void setFormattedMatrix() {
        int sumVertices = graph.getSumVertices();
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-5s |", ""));
        sb.append(appendLabels(sumVertices));

        sb.append("\n");
        sb.append(appendInterline(sumVertices, "      "));
        sb.append(appendInterline(sumVertices, "------"));

        for (int i = 0; i < sumVertices; i++) {
            sb.append(String.format("%-5s |", graph.getVertex(i).getLabel()));
            for (int j = 0; j < sumVertices; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    sb.append(String.format(" %4s |", "\u221E"));
                } else {
                    sb.append(String.format(" %4d |", matrix[i][j]));
                }
            }
            sb.append("\n");
            sb.append(appendInterline(sumVertices, "------"));
        }

        this.formattedMatrix =  sb.toString();
    }

    private String appendLabels(int sumVertices) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sumVertices; i++) {
            sb.append(String.format(" %4s |", graph.getVertex(i).getLabel()));
        }
        return sb.toString();
    }

    private String appendInterline(int sumVertices, String string) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-5s|", string));
        sb.append(String.valueOf(String.format("%4s|", string)).repeat(sumVertices));
        sb.append("\n");

        return sb.toString();
    }


}
