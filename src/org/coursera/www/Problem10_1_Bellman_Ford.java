package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Problem10_1_Bellman_Ford {
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
                   // graph.get(v1).add(new Edge(v2,e));
                    graph.get(v2).add(new Edge(v1,e));

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
        int path[][] = new int[2][number_of_vertex + 1];
        for (int i = 1; i < number_of_vertex + 1; i++) {
            Integer min = bellman_Ford(graph,i,number_of_vertex, path);
            if (min == null) {
                System.out.println("NULL");
                break;
            }
            if (min_min > min.intValue()) {
                min_min = min;
            }
        }
        System.out.println(min_min);
    }
    
    static Integer bellman_Ford(List<List<Edge>> graph, int s, int number_of_vertex, int path[][]) {
        for (int i = 1; i < number_of_vertex + 1; i++) {
            path[0][i] = Integer.MAX_VALUE / 2;
        }
        path[0][s] = 0;
        for (int i = 1; i < number_of_vertex; i++) {
            int flag = 0;
            for (int j = 1; j < number_of_vertex + 1; j++) {
                if (j == s) {
                    continue;
                }
                path[1][j] = path[0][j];
               // System.out.println(j+" "+path[1][j]);
                for (Edge e:graph.get(j)) {
                    if (e.cost + path[0][e.vertex] < path[1][j]) {
                        path[1][j] = e.cost + path[0][e.vertex];
                        flag = 1;
                       //System.out.println(s+" "+j+" "+e.cost+","+path[0][e.vertex]+","+path[0][j]);
                    }
                }
            }
            if (flag == 0) {
                //System.out.println(s+" "+i);
                break;
            }
            for (int j = 1; j < number_of_vertex + 1; j++) {
                if (j == s) {
                    continue;
                }
                path[0][j] = path[1][j];
            }
        }
        
        int min = Integer.MAX_VALUE / 2;
        for (int j = 1; j < number_of_vertex + 1; j++) {
            if (j == s) {
                continue;
            }
            if (min > path[0][j]) {
                min = path[0][j];
              //  System.out.println(j+" "+min);
            }
            for (Edge e:graph.get(j)) {
                if (e.cost + path[0][e.vertex] < path[0][j] ) {
                  //  System.out.println(s+" "+j+" "+e.vertex+" "+e.cost+","+path[0][e.vertex]+","+path[0][j]);
                    return null;
                }
            }
        }
        return min;
    }
    static class Edge {
        int cost, vertex;
        Edge(int v, int c) {
            cost = c;
            vertex = v;
        }
    }

}


