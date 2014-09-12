package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Problem9_1_knapsack {
    public static void main(String args[]) {
        
        ArrayList<Item> itemList = new ArrayList<Item>();
        int knapsack_size = 0;
        int number_of_items = 0;
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\knapsack1.txt");
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
        
        int knapsack_array[][] = new int[number_of_items][knapsack_size+1]; 
        for (int j = 0; j < knapsack_size; j++) {
            int w_i = itemList.get(0).weight;
            int v_i = itemList.get(0).value;
            if (w_i <= j) {
                knapsack_array[0][j] = v_i;
            } else {
                knapsack_array[0][j] = 0;
            }
        }
        for (int i = 1; i < number_of_items; i++) {
            for (int j = 0; j < knapsack_size+1; j++) {
                int w_i = itemList.get(i).weight;
                int v_i = itemList.get(i).value;
                if (w_i <= j) {
                    knapsack_array[i][j] = Math.max(knapsack_array[i-1][j], knapsack_array[i-1][j-w_i]+v_i);
                } else {
                    knapsack_array[i][j] = knapsack_array[i-1][j];
                }
               // System.out.println(i+","+j+","+knapsack_array[i][j]);
            }
        }
        
        System.out.println(knapsack_array[number_of_items-1][knapsack_size]);
    }
}

class Item {
    int value, weight;
    Item(int v, int w) {
        value = v;
        weight = w;
    }
}