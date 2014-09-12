package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.coursera.www.Problem11_1_Traveling_Salesman_Problem.Coord;

public class Problem12_1_2SAT_Papadimitrious_Algorithm {
    public static void main(String args[]) {
        int number_variables = 0;
        List<List<Clause>> clauses = new ArrayList<List<Clause>>();
        try{
            BufferedReader br = null;
            try{
                FileReader fr = new FileReader("C:\\projects\\2sat6.txt");
                br = new BufferedReader(fr);
                
                String line = br.readLine();
                number_variables = Integer.parseInt(line);
                line = br.readLine();
                for (int i = 0; i < number_variables + 1; i++) {
                    clauses.add(new ArrayList());
                }
                while (line != null) {
                    String []numbers = line.split(" ");
                    int v1 = Integer.parseInt(numbers[0]);
                    int v2 = Integer.parseInt(numbers[1]);
                    clauses.get(Math.abs(v1)).add(new Clause(v1, v2));
                    clauses.get(Math.abs(v2)).add(new Clause(v2, v1));
                    line = br.readLine();
                }
            }finally{
                br.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //reduce variables that only show negatively or positively
        int count = number_variables;
        for (int k = 0; k < 100; k++) {
            //System.out.println(count);
            for (int i = 1; i <= number_variables; i++) {
                if (clauses.get(i).size() > 0) {
                    boolean bool = clauses.get(i).get(0).b1;
                    int flag = 1;
                    for (Clause clause : clauses.get(i)) {
                        if (clause.b1 != bool) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                      //  System.out.println(i+" "+clauses.get(i));
                        for (Clause clause : clauses.get(i)) {
                            clauses.get(clause.v2).remove(clause);
                            count--;
                        }
                        clauses.get(i).clear();
                    }
                }
            }
        }
        Random rand = new Random(9999999);
        for (int k = 0; k < Math.log(count); k++) {
            boolean []trial = new boolean[number_variables + 1];
            List<Clause> falses = new ArrayList<Clause>();
            for (boolean bool : trial) {
                bool = rand.nextBoolean();
            }
            for (List<Clause> variable : clauses) {
                for (Clause clause : variable) {
                    if ((clause.b1 ^ trial[clause.v1]) && (clause.b2 ^ trial[clause.v2])) {
                        if (!falses.contains(clause)) {
                            falses.add(clause);
                        }
                    }
                }
            }
            for (int i = 0; i < 2 * count * count; i++) {
                System.out.println(falses.size());
                Clause turnover = falses.get(rand.nextInt(falses.size()));
                int flip = 0;
                if (rand.nextBoolean()) {
                    flip = turnover.v1;
                } else {
                    flip = turnover.v2;                  
                }
                trial[flip] = !trial[flip];
                for (Clause clause : clauses.get(flip)) {
                    if ((clause.b1 ^ trial[clause.v1]) && (clause.b2 ^ trial[clause.v2])) {
                        if (!falses.contains(clause)) {
                            falses.add(clause);
                        }
                    } else {
                        if (falses.contains(clause)) {
                            falses.remove(clause);
                        }
                    }
                }
                if (falses.size() == 0) {
                    System.out.println(true);
                    return;
                }
            }
        }
        System.out.println(false);
    }
    static private class Clause {
        int v1, v2;
        boolean b1, b2;
        Clause(int v1, int v2) {
            if (v1 < 0) {
                this.v1 = -v1;
                b1 = false;
            } else {
                this.v1 = v1;
                b1 = true;
            }
            if (v2 < 0) {
                this.v2 = -v2;
                b2 = false;
            } else {
                this.v2 = v2;
                b2 = true;
            }
        }
        Clause reverse() {
            Clause newClause = new Clause(this.v2, this.v1);
            newClause.b1 = this.b2;
            newClause.b2 = this.b1;
            return newClause;
        }
        @Override public boolean equals(Object other) {
            boolean result = false;
            if (other instanceof Clause) {
                Clause that = (Clause) other;
                result = ((this.v1 == that.v1 && this.v2 == that.v2 && this.b1 == that.b1 && this.b2 == that.b2) || 
                        (this.v1 == that.v2 && this.v2 == that.v1 && this.b1 == that.b2 && this.b2 == that.b1));
            }
            return result;
        }
    }
}
