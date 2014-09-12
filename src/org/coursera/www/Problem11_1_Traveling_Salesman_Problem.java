package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Problem11_1_Traveling_Salesman_Problem {
    public static void main(String args[]) {
        int number_cities = 0;
        List<Coord> cities = new ArrayList<Coord>();
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\tsp.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                number_cities = Integer.parseInt(line);

                line = br.readLine();
                
                while (line != null) {
                    String []numbers = line.split(" ");
                    float v1 = (float) Double.parseDouble(numbers[0]);
                    float v2 = (float) Double.parseDouble(numbers[1]);
                    cities.add(new Coord(v1, v2));
                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        int size = 1 << number_cities;
        float [][]dist = new float[size][number_cities];
        for (int i = 0; i < size; i++) {
            dist[i][0] =  Float.MAX_VALUE / 10.0f;
            for (int j = 1; j < number_cities; j++) {
                dist[i][j] = -1.0f;
            }
        }
        dist[1][0] = 0;
        
        float min = Float.MAX_VALUE;
        for (int i = 0; i < number_cities; i++) {
            dist[size - 1][i] = recursion(size - 1, i, cities, dist);
            if (min > dist[size - 1][i] + computeDist(cities.get(i), cities.get(0))) {
                min = dist[size - 1][i] + computeDist(cities.get(i), cities.get(0));
            }
        }
        System.out.println(min);
    }
    
    static float recursion(int set, int dest,List<Coord> cities, float[][] dist) {
        if (dist[set][dest] >= 0) {
            return dist[set][dest];
        }
        float min = Float.MAX_VALUE;
        int sp = set - (1 << dest);
        int spp = sp;
        int i = 0;
        while (spp > 0) {
            if ((spp & 1) == 0) {
                i++;
                spp >>= 1;
                continue;
            }
            float distp = computeDist(cities.get(i), cities.get(dest)) + recursion(sp, i, cities, dist);
            if (distp < min) {
                min = distp;
            }
            i++;
            spp >>= 1;
        }
        dist[set][dest] = min;
        return min;
    }
    
    static float computeDist(Coord c1, Coord c2) {
        return (float) Math.sqrt((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y));
    }
    static class Coord{
        float x = 0;
        float y = 0;
        public Coord(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
