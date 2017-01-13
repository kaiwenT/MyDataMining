package com.hust.dm.util;

import java.util.List;

public class Threshold {
	
	private double threshold;
	
	private double max;
	
	private double min;
	
	public Threshold(List<double[]> vectors){
		double sim = 0;
		max = min = 0;
		double si = 0;
		CosSimilarity cosSim = new CosSimilarity();
		for(double[] v1 : vectors){
			for(double[] v2 : vectors){
				if(!v1.equals(v2)){
					si = cosSim.calculate(v1, v2);
					sim += si;
					if(si > max)	max = si;
					if(si != 0 && si < min)	min = si;
				}
			}
		}
		int n = vectors.size();
		threshold = sim / (n*(n-1)/2);//n-1+n-2+...+1 = n*(n-1)/2
	}
	
	public double getThreshold(){		
		return threshold;
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}

	
}
