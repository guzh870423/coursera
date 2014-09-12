package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;

public class Problem8_2_Hamming_distance {
    public static void main(String args[]) {
        
        int []nodesTable = null;
        int []nodes = null;
        int numNodes = 0, numBits = 0;
        int dup = 0;
        try{
            String path = "C:\\projects\\clustering_big.txt";
            BufferedReader br = null;
            int count = 1, linecount = 0;
            try{
                br = new BufferedReader(new FileReader(path));
                String line;
                line = br.readLine();
                String[] numbers = line.split(" ");
                numNodes = Integer.parseInt(numbers[0]);
                nodes = new int[numNodes + 1];
                numBits = Integer.parseInt(numbers[1]);
                nodesTable = new int[(int) Math.pow(2,numBits)];
                line = br.readLine();
                while (line != null) {
                    numbers = line.split(" ");
                    int bits[] = new int[numBits];
                    for (int i = 0; i < numBits; i++) {
                        bits[i] = Integer.parseInt(numbers[i]);
                    }
                    HammingNode ham = new HammingNode(numBits,bits);
                    if (nodesTable[ham.value] != 0) {
                        dup++;
                    } else {
                        nodesTable[ham.value] = count;
                        nodes[count] = ham.value;
                        count++;
                    }
                    linecount++;
                    line = br.readLine();
                }
            }finally {
                br.close();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        UnionFind myUnion = new UnionFind(numNodes + 1);
        int c = numNodes - dup;
        for (int i = 1; i < numNodes - dup; i++) {
            int nodeValue = nodes[i];
            int s1 = 1;
            for (int j = 0; j < numBits; j++) {
                int node1Value = nodeValue ^ s1;
                int node1 = nodesTable[node1Value];
                if (node1 != 0) {
                    if (!myUnion.find(i, node1)) {
                        myUnion.union(i, node1);
                        c--;
                    }
                }
                int s2 = s1<<1;
                for (int k = j + 1; k < numBits; k++) {
                    int node2Value = nodeValue ^ (s1 + s2);
                    int node2 = nodesTable[node2Value];
                    if (node2 != 0) { 
                        if (!myUnion.find(i, node2)) {
                            myUnion.union(i, node2);

                            c--;
                        }
                    }
                    s2 <<= 1;
                }
                s1 <<= 1;
            }
        }
        
        System.out.println(c);
    }
}

class HammingNode {
    int value = 0;

    HammingNode(int n, int b[]) {
        for (int i = 0; i < n; i++) {
            value = value * 2 + b[i];
        }
    }
    
    HammingNode(int n, boolean b[]) {
        for (int i = 0; i < n; i++) {
            if (b[i]){
                value = value * 2 + 1;
            } else {
                value = value * 2;
            }

        }
    }
}


    
