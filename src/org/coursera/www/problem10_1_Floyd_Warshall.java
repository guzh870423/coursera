package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class problem10_1_Floyd_Warshall {
    public static void main(String args[]) {
        List<List<Edge>> graph = null;
        int number_of_edge = 0;
        int number_of_vertex = 0;
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\g3.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                number_of_vertex = Integer.parseInt(line.split(" ")[0]);
                number_of_edge = Integer.parseInt(line.split(" ")[1]);
                
                graph = new ArrayList<List<Edge>>();
                for (int i = 0; i < number_of_vertex + 1; i++) {
                    graph.add(new ArrayList<Edge>());
                }
                line = br.readLine();
                
                while (line != null) {
                    String []numbers = line.split(" ");
                    int v1 = Integer.parseInt(numbers[0]);
                    int v2 = Integer.parseInt(numbers[1]);
                    int e = Integer.parseInt(numbers[2]);
                    graph.get(v1).add(new Edge(v2,e));
                    //graph.get(v2).add(new Edge(v1,e));

                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        int min_min = Integer.MAX_VALUE / 2;
        int path[][] = new int[number_of_vertex + 1][number_of_vertex + 1];
        for (int i = 1; i < number_of_vertex + 1; i++) {
            for (int j = 1; j < number_of_vertex + 1; j++) {
                if (i == j) {
                    path[i][i] = 0;
                } else {
                    path[i][j] = Integer.MAX_VALUE / 2;
                }
                
            }
        }
        for (int i = 1; i < number_of_vertex + 1; i++) {
            for (Edge e:graph.get(i)) {
                path[i][e.vertex] = e.cost;
            }
        }

        for (int k = 1; k < number_of_vertex + 1; k++) {
            for (int i = 1; i < number_of_vertex + 1; i++) {
                for (int j = 1; j < number_of_vertex + 1; j++) {
                    floyd_Marshall(i, j, k, path);
                    if (i == j && path[i][i] < 0) {
                        System.out.println("NULL");
                        return;
                    }
                    
                    if (min_min > path[i][j]) {
                        min_min = path[i][j];
                    }
                }
            }
        }
        
        System.out.println(min_min);
    }
    
    static void floyd_Marshall(int s, int d, int limit, int path[][]) {
        if (s == limit || d == limit) {
            return;
        }
        if (path[s][limit] + path[limit][d] < path[s][d]) {
            path[s][d] = path[s][limit] + path[limit][d];
        }
        
    }
   

    static class Edge {
        int cost, vertex;
        Edge(int v, int c) {
            cost = c;
            vertex = v;
        }
    }

}
