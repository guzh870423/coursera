package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Problem5_Dijkstra {
	public static void main(String []args){
		ArrayList<ArrayList<Vertex>> ar = new ArrayList<ArrayList<Vertex>>();
		BufferedReader br = null;
		
		try{
			try{
				br = new BufferedReader(new FileReader("c:\\projects\\dijkstraData.txt"));
				String line = br.readLine();
				
				while(line!=null){
					String []pairs= line.split(" |\t");
					ar.add(new ArrayList<Vertex>());
					for(int i =1;i<pairs.length;i++){
						String []numbers = pairs[i].split(",");
						int v = Integer.parseInt(numbers[0]);
						int d = Integer.parseInt(numbers[1]);
						ar.get(ar.size()-1).add(new Vertex(v,d));
					}
					line = br.readLine();
				}
			}finally{
				br.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Dijkstra(ar);
	}
	
	public static void Dijkstra(ArrayList<ArrayList<Vertex>> ar){
		int []dist = new int[ar.size()];
		for(int i=0;i<dist.length;i++){
			dist[i] = 1000000;
		}

		Heap heap = new Heap();
		dist[0] = 0;
		for(int i=0;i<dist.length;i++){
			heap.add(new Vertex(i+1,dist[i]));
		}
		while(heap.size()>0){
			Vertex min = heap.minHeapify();
			dist[min.v-1] = min.d;
			heap.update(ar, min);
		}
		
		int []output = {7,37,59,82,99,115,133,165,188,197};
		for(int o:output){
			System.out.println(dist[o-1]);
		}
	}
	
}

class Vertex{
	public int v=0;
	public int d=0;
	Vertex(int v,int d){
		this.v=v;
		this.d=d;
	}
}

class Heap{
	ArrayList<Vertex> a = new ArrayList<Vertex>();
	public int size(){
		return a.size();
	}
	public void add(Vertex v){
		a.add(v);
		int n = a.size()-1;
		while(n>0){
			int p = (n-1)/2;
			if(a.get(p).d>a.get(n).d){
				Vertex temp = a.get(n);
				a.set(n, a.get(p));
				a.set(p,temp);
			}
			else{
				break;
			}
			n = p;
		}
	}
	
	public void delete(){
		a.remove(a.size()-1);
	}
	public Vertex minHeapify(){
		Vertex min = a.get(0);
		a.set(0,a.get(a.size()-1));
		this.delete();
		int n = 0;
		while(2*n+1<a.size()){
			int c1 = 2*n+1;
			int c2 = 2*n+2;
			int larger = n;
			if(a.get(c1).d<a.get(n).d){
				larger = c1;
			}
			if(c2<a.size()&&a.get(c2).d<a.get(larger).d){
				larger = c2;
			}
			if(larger!=n){
				Vertex temp = a.get(n);
				a.set(n, a.get(larger));
				a.set(larger,temp);
				n = larger;
			}
			else{
				break;
			}
		}
		return min;
	}

	public void set(int n, Vertex vertex){
		a.set(n, vertex);
		while(n>0){
			int p = (n-1)/2;
			if(a.get(p).d>a.get(n).d){
				Vertex temp = a.get(n);
				a.set(n, a.get(p));
				a.set(p,temp);
			}
			else{
				return;
			}
			n = p;
		}
		while(2*n+1<a.size()){
			int c1 = 2*n+1;
			int c2 = 2*n+2;
			int larger = n;
			if(a.get(c1).d<a.get(n).d){
				larger = c1;
			}
			if(c2<a.size()&&a.get(c2).d<a.get(larger).d){
				larger = c2;
			}
			if(larger!=n){
				Vertex temp = a.get(n);
				a.set(n, a.get(larger));
				a.set(larger,temp);
			}
			else{
				return;
			}
			n = larger;
		}
	}
	public void update(ArrayList<ArrayList<Vertex>> ar, Vertex min){
		for(int i=0;i<ar.get(min.v-1).size();i++){
			for(int j=0;j<a.size();j++){
				if(ar.get(min.v-1).get(i).v==a.get(j).v){
					if(min.d+ar.get(min.v-1).get(i).d<a.get(j).d){
						this.set(j,new Vertex(a.get(j).v,min.d+ar.get(min.v-1).get(i).d));
					}
				}
			}
		}
		
	}
}