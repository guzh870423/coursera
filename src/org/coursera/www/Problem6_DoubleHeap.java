package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem6_DoubleHeap {
	public static void main(String []args){
		Comparator<Integer> cmp = new Comparator<Integer>()
				{
				    public int compare( Integer x, Integer y )
				    {
				        return y - x;
				    }
				};
		PriorityQueue<Integer> low = new PriorityQueue<Integer>(1000,cmp);
		PriorityQueue<Integer> high = new PriorityQueue<Integer>();	
		BufferedReader br = null;
		try{
			try{
				String path = new String("c:\\projects\\Median.txt");
				br = new BufferedReader(new FileReader(path));
				String line = br.readLine();
				int sum = 0;
				while(line != null){
					int l = Integer.parseInt(line);	
					if(!high.isEmpty()&&l>high.peek()){
						high.add(l);
					}else{
						low.add(l);
					}
					if(low.size()>high.size()+1){
						high.add(low.poll());
					}
					if(low.size()<high.size()){
						low.add(high.poll());
					}
					System.out.println(low.peek());
					sum = (sum + low.peek())%10000;
					line = br.readLine();
				}
				System.out.println(sum);

			}finally{
				br.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
