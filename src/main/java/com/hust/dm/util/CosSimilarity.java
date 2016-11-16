package com.hust.dm.util;

import java.util.List;

public class CosSimilarity {

	public double multiply(double[] v1, double[] v2){
		double m = 0;
		int len = v1.length < v2.length ? v1.length : v2.length;		
		for(int i = 0 ; i < len ; i++){
			m += v1[i] * v2[i];
		}		
		return m;
	}
	
	public double module(double[] v){
		double m = 0;		
		for(int i = 0 ; i < v.length; i++){
			m += v[i] * v[i];
		}		
		return Math.sqrt(m);
	}

	public double calculate(double[] v1, double[] v2){
		double sim = 0;		
		sim = Math.abs(multiply(v1, v2)) / module(v1) * module(v2);		
		return sim;
	}
	
	public double calculate(double[] vector, List<double[]> tmpVectors) {
		// TODO Auto-generated method stub
		double sim = 0;
		for(double[] v : tmpVectors){
			sim += calculate(vector,v);
		}
		return sim / tmpVectors.size();
	}

}