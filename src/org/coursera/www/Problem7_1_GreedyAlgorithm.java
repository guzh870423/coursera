package org.coursera.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Problem7_1_GreedyAlgorithm {

	public static void main(String args[]) {
		Comparator<Job> comparator1 = new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				if ((job1.weight - job1.length) - (job2.weight - job2.length) != 0) {
					return ((job2.weight - job2.length) - (job1.weight - job1.length));
				} else {
					return job2.weight - job1.weight;
				}
			}
		};
		Comparator<Job> comparator2 = new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				return (int) (10000 * ((1.0 * job2.weight / job2.length) - (1.0 * job1.weight / job1.length)));

			}
		};
		
		List<Job> jobs = new ArrayList<Job>();
		try{
			BufferedReader br = null;
			try{
				FileReader fr = new FileReader("C:\\projects\\jobs.txt");
				br = new BufferedReader(fr);
				
				String line = br.readLine();
				line = br.readLine();
				
				while (line != null) {
					String []job = line.split(" ");
					jobs.add(new Job(Integer.parseInt(job[0]), Integer.parseInt(job[1])));
					line = br.readLine();
				}
			}finally{
				br.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(jobs, comparator1);
		long sum = 0;
		long time = 0;
		for(Job job : jobs) {
			System.out.println(job.weight);
			time += job.length;
			sum += job.weight * time;
		}
		System.out.println(sum);
	}

}
class Job {
	int length = 0;
	int weight = 0;
	public Job(int w, int l){
		weight = w;
		length = l;
	}
}

