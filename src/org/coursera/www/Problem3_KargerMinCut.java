package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Problem3_KargerMinCut {
	public static void main(String []args){
		String path = "c:\\projects\\kargerMinCut.txt";
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			
			while(line!=null){
				String []numbers = line.split("\t");
				a.add(new ArrayList<Integer>());

				for(String number:numbers){
					a.get(a.size()-1).add(Integer.parseInt(number));
				}
				line = br.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int min = Integer.MAX_VALUE;
		int min_cut =0;
		for(int n = 1000;n>0;n--){
			ArrayList<ArrayList<Integer>> b = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<a.size();i++){
				b.add(new ArrayList<Integer>());
				for(int j=0;j<a.get(i).size();j++){
					b.get(i).add( a.get(i).get(j));
				}
			}
			min_cut = contract(b);
			if(min_cut<min) {
				min = min_cut;
				System.out.println(n+" "+min_cut);
			}

		}
		System.out.println(min);
		
	}

	
	public static int contract(ArrayList<ArrayList<Integer>> a){
		Random random = new Random();
		while(a.size() > 2){
			int total = 0;
			for(ArrayList<Integer> ai:a){
				total += (ai.size()-1);
			}

			//System.out.println(total);
			
			int rdm = random.nextInt(total)+1;
			int m = 0;
			while(rdm > a.get(m).size()-1){
				rdm -= (a.get(m).size()-1);
				++m;
			}
			int i1 = a.get(m).get(0); 
			int i2 = a.get(m).get(rdm); 
			//System.out.println(a.size()+" "+i1+" "+i2);
			ArrayList<Integer> b = null; 
			int flag=0;
			for(int i = 0; i< a.size();++i){
				if(a.get(i).get(0) == i2){
				//	System.out.println(a.get(i));
					b = new ArrayList<Integer>(a.get(i));
					b.remove(0);
					//System.out.println(i2);
					//System.out.println(b);
					a.remove(i);
					flag=1;
					continue;
				}
			}
			if(flag==0) return -1;

			//System.out.println(i1+" "+i2);
			for(int i = 0; i< a.size();++i){
				if(a.get(i).get(0) == i1){
					//System.out.println(a.get(i));
					flag=0;
					for(int j = 1;j<a.get(i).size();++j){
						
						if(a.get(i).get(j).equals(i2)){
							a.get(i).remove(j);
							j--;
							flag=1;
						}						
					}

					if(flag==1&&b!=null) a.get(i).addAll(b);
					delete_self(a.get(i));
					continue;
				} 
				//System.out.println(i1+" "+i2);				

				for(int j = 1;j < a.get(i).size();++j){
					//System.out.println(i+" "+j+" "+a.get(i).size());	
					if(a.get(i).get(j).equals(i2)){
						a.get(i).set(j,i1);
					}
				}	
			//	delete_self(a.get(i));
			}
			//System.out.println(a.size());
		}
		//System.out.println(a.get(0));
		//System.out.println(a.get(1));
		return a.get(0).size()-1;
	}

	public static void delete_self(ArrayList<Integer> c){
		
		for(int i = 1;i<c.size();++i){

			if(c.get(i).equals(c.get(0)) ){
				c.remove(i);
				i--;
			}
		}
	}
}
