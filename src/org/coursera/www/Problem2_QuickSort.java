package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
  
public class Problem2_QuickSort {
	static int count = 0;
	
	public static void main(String []args){
		
		int []a = new int[10000];
		int length = 0;
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new FileReader("c:\\projects\\QuickSort.txt"));
			//br = new BufferedReader(new FileReader("c:\\projects\\123.txt"));
			String line = br.readLine();
			
			
			while(line != null){
				a[length] = Integer.parseInt(line);
				length++;
				line = br.readLine();		
			} 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			//br.close();
		}
		
		
		quickSort3(a,0,length-1);
		System.out.println(count);
		for(int ai:a){
	//		if (ai!=0) System.out.println(ai);
		}
	}

	public static void quickSort2(int []a, int i, int j){
		if (i==j){
			return;
		}
		count = count + (j-i);
	//	System.out.println(j-i);
		
		
		int pivot = a[j];
		a[j] = a[i];
		a[i] = pivot;
		int m=i,n=i;
		while(n<j){
			n++;
			if(a[n] < pivot){
				int temp = a[n];
				a[n] = a[m+1];
				a[m+1] = temp;
				m++;
			}
		}
		a[i] = a[m];
		a[m] = pivot;	
		
		if(m-1>=i) {quickSort2(a,i,m-1);}
		if(j>=m+1) {quickSort2(a,m+1,j);}
		
		return;
		
	}
	

	public static void quickSort3(int []a, int i, int j){
		if (i==j){
			return;
		}
		count = count + (j-i);
	//	System.out.println(j-i);
		int pivot;
		int md = (i+j)/2;
		if((a[i]-a[j])*(a[i]-a[md])<0){
			pivot = a[i];
		}
		else if((a[j]-a[i])*(a[j]-a[md])<0){
			pivot = a[j];
			a[j] = a[i];
			a[i] = pivot;
		}
		else{
			pivot = a[md];
			a[md] = a[i];
			a[i] = pivot;
		}
			

		int m=i,n=i;
		while(n<j){
			n++;
			if(a[n] < pivot){
				int temp = a[n];
				a[n] = a[m+1];
				a[m+1] = temp;
				m++;
			}
		}
		a[i] = a[m];
		a[m] = pivot;	
		
		if(m-1>=i) {quickSort3(a,i,m-1);}
		if(j>=m+1) {quickSort3(a,m+1,j);}
		
		return;
		
	}
	
	public static void quickSort1(int []a, int i, int j){
		if (i==j){
			return;
		}
		count = count + (j-i);
	//	System.out.println(j-i);
		
		int pivot = a[i];
		int m=i,n=i;
		while(n<j){
			n++;
			if(a[n] < pivot){
				int temp = a[n];
				a[n] = a[m+1];
				a[m+1] = temp;
				m++;
			}
		}
		a[i] = a[m];
		a[m] = pivot;	
		
		if(m-1>=i) {quickSort1(a,i,m-1);}
		if(j>=m+1) {quickSort1(a,m+1,j);}
		
		return;
		
	}
}

