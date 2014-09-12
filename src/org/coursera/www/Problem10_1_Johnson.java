package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Problem10_1_Johnson {
    public static void main(String args[]) {
        List<List<Problem10_1_Bellman_Ford.Edge>> graph = null;
        int number_of_edge = 0;
        int number_of_vertex = 0;
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\123.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                number_of_vertex = Integer.parseInt(line.split(" ")[0]);
                number_of_edge = Integer.parseInt(line.split(" ")[1]);
                
                graph = new ArrayList<List<Problem10_1_Bellman_Ford.Edge>>();
                for (int i = 0; i < number_of_vertex + 1; i++) {
                    graph.add(new ArrayList<Problem10_1_Bellman_Ford.Edge>());
                }
                line = br.readLine();
                
                while (line != null) {
                    String []numbers = line.split(" ");
                    int v1 = Integer.parseInt(numbers[0]);
                    int v2 = Integer.parseInt(numbers[1]);
                    int e = Integer.parseInt(numbers[2]);
                   // graph.get(v1).add(new Edge(v2,e));
                    graph.get(v2).add(new Problem10_1_Bellman_Ford.Edge(v1,e));

                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        graph.add(new ArrayList<Problem10_1_Bellman_Ford.Edge>());
        int path[][] = new int[2][number_of_vertex + 2];
        for (int i = 1; i < number_of_vertex + 1; i++) {
            graph.get(number_of_vertex + 1).add(new Problem10_1_Bellman_Ford.Edge(i,0));
        }        
        
        Integer min = Problem10_1_Bellman_Ford.bellman_Ford(graph,
                number_of_vertex + 1,number_of_vertex + 1, path);
        
        int min_min = Integer.MAX_VALUE / 2;

        for (int i = 1; i < number_of_vertex + 1; i++) {

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
    

}


