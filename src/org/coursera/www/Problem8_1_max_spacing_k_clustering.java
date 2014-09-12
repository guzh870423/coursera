package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Problem8_1_max_spacing_k_clustering {
    public static void main(String args[]) {
        PriorityQueue<Edge> edgeList = null;
        int numNodes = 0;
        Comparator<Edge> cmp = new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return e1.cost - e2.cost;
            }
        };
        try{
            String path = "C:\\projects\\clustering1.txt";
            BufferedReader br = null;
            try{
                br = new BufferedReader(new FileReader(path));
                String line;
                line = br.readLine();
                numNodes = Integer.parseInt(line);
                edgeList = new PriorityQueue(40 * numNodes, cmp);
                line = br.readLine();
                while (line != null) {
                    String numbers[] = line.split(" ");
                    int n1 = Integer.parseInt(numbers[0]);
                    int n2 = Integer.parseInt(numbers[1]);
                    int c = Integer.parseInt(numbers[2]);
                    edgeList.add(new Edge(n1, n2, c));
                    
                    line = br.readLine();
                }
            }finally {
                br.close();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        UnionFind cluster = new UnionFind(numNodes + 1);
        int k = numNodes;
        while(!edgeList.isEmpty() && k > 4) {
            Edge min = edgeList.poll();
            System.out.println(min.node1+" "+min.node2+" "+min.cost);
            if (!cluster.find(min)) {
                k--;
                cluster.union(min);
            }
        }
        while (cluster.find(edgeList.peek())) {
            edgeList.poll();
        }
        System.out.println(edgeList.peek().cost);
    }
}

class Edge {
    int node1, node2, cost;
    Edge(int n1, int n2, int c) {
        node1 = n1;
        node2 = n2;
        cost = c;
    }
}

class UnionFind {
    int parent[], rank[];
    public UnionFind(int N){
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    private int track(int n) {
        int p = parent[n];
        while(p != n) {
            n = p;
            p = parent[n];
        }
        return p;
    }
    
    public void union(int n1, int n2) {
        int r1 = track(n1);
        int r2 = track(n2);
        if (r1 > r2) {
            parent[r2] = r1;
        } else if (r2 > r1) {
            parent[r1] = r2;
        } else {
            parent[r2] = r1;
            rank[r1]++;
        }
    }
    
    public void union(Edge e) {
        union(e.node1,e.node2);
    }
    
    public boolean find(int n1, int n2) {
        return (track(n1) == track(n2));
    }
    public boolean find(Edge e) {
        return find(e.node1, e.node2);
    }
}
