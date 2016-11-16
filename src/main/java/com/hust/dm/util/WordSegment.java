package com.hust.dm.util;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class WordSegment {

	/**
	 * ÖÐÎÄ·Ö´Ê
	 * @param str
	 * @return
	 */
	public String[] parse(String str) {
		Result res = ToAnalysis.parse(str);
		String[] words = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			words[i] = res.get(i).getName();
		}
		return words;
	}
}
