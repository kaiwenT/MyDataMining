package com.hust.dm.util;

import java.util.List;

public class Threshold {
	
	private double threshold;
	
	public double getThreshold(List<double[]> vectors){
		double sim = 0;
		CosSimilarity cosSim = new CosSimilarity();
		for(double[] v1 : vectors){
			for(double[] v2 : vectors){
				if(!v1.equals(v2)){
					sim += cosSim.calculate(v1, v2);
				}
			}
		}
		int n = vectors.size();
		threshold = sim / (n*(n-1)/2);//n-1+n-2+...+1 = n*(n-1)/2
		return threshold;
	}

}
