package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Problem7_3_MinimumSpanningTree {
    public static void main(String args[]) {
        int numberNodes = 1;
        ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\edges.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                numberNodes = Integer.parseInt(line.split(" ")[0]);

                for (int i =0; i <= numberNodes; i++) {
                    nodes.add(new ArrayList<Node>());
                }
                line = br.readLine();
                
                while (line != null) {
                    String []job = line.split(" ");
                    int number[] = new int[3];
                    for(int i =0; i < 3; i++) {
                        number[i] = Integer.parseInt(job[i]);
                    }
                    nodes.get(number[0]).add(new Node(number[1],number[2]));
                    nodes.get(number[1]).add(new Node(number[0],number[2]));
                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        

        Comparator<Node> comparator = new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return node1.cost - node2.cost;
            }
        };
        PriorityQueue<Node> heap = new PriorityQueue<Node>(numberNodes,comparator);
        for (int i = 2; i <= numberNodes; i++) {
            heap.add(new Node(i,9999999));
        }
        heap.add(new Node(1,0));
        long sum = 0;
        while (!heap.isEmpty()) {
            Node min = heap.poll();
            System.out.println(min.vertex+" "+min.cost);
            sum += min.cost;
            for (Node i:nodes.get(min.vertex)) {
                Iterator<Node> itor = heap.iterator();
                int flag = 0;
                Node j = null;
                while (itor.hasNext()) {
                    j = itor.next();
                    if (i.vertex == j.vertex && i.cost < j.cost) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    heap.remove(j);
                    heap.add(i);
                }
            }
        }
        System.out.println(sum);
        
    }
}

class Node {
    int vertex;
    int cost;
    public Node(int v, int c) {
        vertex = v;
        cost = c;
    }
}