package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Problem4_StronglyConnectedComponent {
	
	public static void main(String []args){
		
		int max = 875714;
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<max;i++){
			a.add(new ArrayList<Integer>());
			ar.add(new ArrayList<Integer>());
		}
		String path = "C:\\projects\\scc.txt";
		BufferedReader br = null;
		try{
			try{
				br = new BufferedReader(new FileReader(path));
				String line = br.readLine();
				while(line!=null){
					String []numbers = line.split(" ");
					int x = Integer.parseInt(numbers[0]);
					int y = Integer.parseInt(numbers[1]);
					a.get(x-1).add(y);
					ar.get(y-1).add(x);
					line = br.readLine();
				}
				
			}finally{
				br.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		

		scc(ar,a,max,5);
		
	}



	public static int[] scc(ArrayList<ArrayList<Integer>> ar,
			ArrayList<ArrayList<Integer>> a, int max, int output){

		int n = 1;
		int []order = new int[max];
		Boolean []explored = new Boolean[max];
		
		int []b = new int[output+1];
		for(int i=0;i<max;i++){
			explored[i] = false;
		}
		
		for(int i=max;i>0;--i){
		//	System.out.println(i);
			if(!explored[i-1]){
				explored[i-1] = true;
				n=DFS1(ar, i, n, order, explored);
			}
		}
//		Stack<Integer> st = new Stack();
//		for(int i=max;i>0;--i){
//			System.out.println(i);
//			int s = i;
//			if(!explored[s-1]){
//				explored[s-1] = true;
//				while(true){
//					
//					int j;
//					for(j=0;j<a.size();++j){
//						if(a.get(j)[1].equals(s)){
//							if(!explored[a.get(j)[0]-1]){
//								st.push(s);
//								s = a.get(j)[0];
//								explored[s-1] = true;
//								break;
//							}
//						}
//					}
//					if(j==a.size()){
//						order[s-1] = n;
//						n--;
//						if(st.empty()){
//							break;
//						}
//						else s = st.pop();
//					}
//					
//				}
//			}
//			
//		}
		
		//second path
		for(int i=0;i<max;i++){
			explored[i] = false;
		}	
		for(int i=max;i>0;--i){
			System.out.println(i);
			int j;
			for(j=1;j<=max;j++){
				if(order[j-1]==i){
					//System.out.println(i+" "+j);
					break;
				}
			}
			if(!explored[j-1]){
				explored[j-1] = true;
				n=DFS2(a, j, 0, explored);
				b[output] = n;
				for(int k = output;k>0;k--){
					if(b[k]>b[k-1]){
						b[k] = b[k-1];
						b[k-1] = n; 
					}
				}
			}
		}
		
//		for(int i:order){
//			System.out.print(i+" ");
//		}
		for(int i:b){
			System.out.print(i+" ");
		}
		return b;

	}
	
	public static int DFS1(ArrayList<ArrayList<Integer>> ar, int s, int n, int []order, Boolean []explored){
		for(int i=0;i<ar.get(s-1).size();++i){

				if(!explored[ar.get(s-1).get(i)-1]){
					explored[ar.get(s-1).get(i)-1] = true;
					n = DFS1(ar, ar.get(s-1).get(i), n, order, explored);
				}

		}
		
		order[s-1] = n;
		return n+1;
		
	}
	
	public static int DFS2(ArrayList<ArrayList<Integer>> a, int s, int n, Boolean []explored){
		//System.out.println(s);
		for(int i=0;i<a.get(s-1).size();++i){

			if(!explored[a.get(s-1).get(i)-1]){
				explored[a.get(s-1).get(i)-1] = true;
				n = DFS2(a, a.get(s-1).get(i), n, explored);
			}

		}
		
		return n+1;
		
	}
}


