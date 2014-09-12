package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Problem9_2_knapsack_big {
    static HashMap<Integer, Integer> knapsack = new HashMap<Integer, Integer>();
    static int knapsack_size = 0;
    static int number_of_items = 0;
    static ArrayList<Item> itemList = new ArrayList<Item>();
    public static void main(String args[]) {


        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\knapsack_big.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                knapsack_size = Integer.parseInt(line.split(" ")[0]);
                number_of_items = Integer.parseInt(line.split(" ")[1]);

                line = br.readLine();
                
                while (line != null) {
                    String []item = line.split(" ");

                    itemList.add(new Item(Integer.parseInt(item[0]), Integer.parseInt(item[1])));
                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        int max = compute_knapsack(number_of_items - 1, knapsack_size);
        System.out.println(max);
    }
    
    static int compute_knapsack(Integer i, Integer j) {
        if (knapsack.containsKey(i * (knapsack_size + 1) + j)) {
            return knapsack.get(i * (knapsack_size + 1) + j);
        }
        int w_i = itemList.get(i).weight;
        int v_i = itemList.get(i).value;
        if (i == 0) {
            if (j >= w_i) {
                knapsack.put( j, v_i);
                return v_i;
            } else {
                knapsack.put( j, 0);
                return 0;
            }
        }
        if (j >= w_i) {
            int result = Math.max(compute_knapsack(i-1,j), compute_knapsack(i-1,j-w_i)+v_i);
            knapsack.put(i * (knapsack_size + 1) + j, result);
            return result;
        } else {
            int result = compute_knapsack(i-1,j);
            knapsack.put(i * (knapsack_size + 1) + j, result);
            return result;
        }
        
    }
}
