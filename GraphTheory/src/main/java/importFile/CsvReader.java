package importFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader {
    private String path;
    private int[][] adjacencyMatrix;
    private ArrayList<String> vertexList;

    public CsvReader(String path) {
        setPath(path);
    }

    private void setPath(String path) {
        if (path!= null) {
            this.path = path;
        }
    }

    public void readFile() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(path));
            int numVertices = 0;
            int rowIndex = 0;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(";");

                if (numVertices == 0) {
                    numVertices = row.length - 1;
                    createVertexList(row);
                    adjacencyMatrix = new int[numVertices][numVertices];
                } else {
                    createAdjacencyMatrix(row, rowIndex);
                    rowIndex++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createVertexList(String[] row) {
        vertexList = new ArrayList<>();

        vertexList.addAll(Arrays.asList(row).subList(1, row.length));
    }

    private void createAdjacencyMatrix(String[] row, int indexRow) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            adjacencyMatrix[indexRow][i] = Integer.parseInt(row[i + 1]);
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public ArrayList<String> getVertexList() {
        return vertexList;
    }
}
