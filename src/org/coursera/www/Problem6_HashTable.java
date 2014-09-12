package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Problem6_HashTable {
	public static void main(String []args){
		Map m = new HashMap();
		ArrayList<Long> pos = new ArrayList<Long>();
		BufferedReader br = null;
		try{
			try{
				String path = new String("c:\\projects\\algo1-programming_prob-2sum.txt");
				br = new BufferedReader(new FileReader(path));
				String line = br.readLine();
				while(line != null){
					Long l = Long.parseLong(line);
					if(l > 0){
						pos.add(l);
					}
					m.put(l,1);
					line = br.readLine();
				}
			}finally{
				br.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int count = 0;
		for(long i = -10000;i<=10000;i++){
			System.out.println(i);
			for(long j:pos){
				if(m.containsKey(i-j)){
					count++;
					break;
				}
			}
			
		}
		System.out.println(count);
	}
	

}
