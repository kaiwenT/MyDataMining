package com.hust.dm.test;
import java.util.ArrayList;
import java.util.List;

import com.hust.dm.util.CosSimilarity;
import com.hust.dm.util.TFIDFConverter;
import com.hust.dm.util.WordSegment;

import org.junit.Test;

public class CanopyTest {

	//原始文本
	private List<String[]> seglist;
	
	//原始文本对应向量
	private List<double[]> vectors;
	
	//
	private List<List<Integer>> resultIndex;
	//
//	private List<List<double[]>> resultVector;
	
	/**
	 * 分词
	 */
	@Test
	public void wordSegment(){
		String str = "七常委观看纪念红军长征胜利80年晚会宁波旅游局通报“赴日游客带走酒店马桶盖”";
		String[] words = new WordSegment().parse(str);
		for(String s :words){
			System.out.print(s+" / ");
		}
	}
	
	@Test
	public void canopy(){
		//初始化原始文本  + 分词
		initSeglist();
//		seglist = init();
		//初始化canopy阈值
		double threshold = 0.06f;
		//显示分词的原始文本
		showSeglist();
		
		//向量转换
		TFIDFConverter converter = new TFIDFConverter();
		vectors = converter.convert(seglist);
		//
		showVectors();
		//canopy算法
		resultIndex = new ArrayList<List<Integer>>();
		List<Integer> tmpIndex = null;
		for(int i = 0 ; i < vectors.size() ; i++){
			double[] vector = vectors.get(i);
			// = new ArrayList<Integer>();
			//i = 0 一个类都没有时，直接添加进resultIndex。
			if(i == 0){
//				List<Integer> tmpIndex = resultIndex.get(i);
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
				continue;
			}
			
			//找到符合相似度要求的类的标志
			boolean isFind = false;
			
			for(int j = 0 ; j < resultIndex.size() ; j++){
				//得到第j个类的向量组
				List<double[]> tmpVectors = getTmpVector(resultIndex.get(j));
				CosSimilarity sim = new CosSimilarity();
				//计算向量与已分的类的向量平均值是否大于阈值，大于则添加到该类
				if(sim.calculate(vector,tmpVectors) > threshold){
					tmpIndex = resultIndex.get(j);
					tmpIndex.add(i);
					//把i加到tmpIndex，然后从resultIndex里删除第j个，最后添加tmpIndex到resultIndex
					resultIndex.remove(j);
					resultIndex.add(tmpIndex);
					isFind = true;
					break;
				}
			}
			
			//与前面的类相似度都不符合要求则新建一个类
			if(!isFind){
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
			}
		}
		System.out.println("tmpIndex.size:"+tmpIndex.size());
		System.out.println("resultIndex.size:"+resultIndex.size());
		//
		showResult();
	}	

	public List<double[]> getTmpVector(List<Integer> list){
		List<double[]> tmp = new ArrayList<double[]>();
		for(int i = 0 ; i < list.size() ; i++){
			tmp.add(vectors.get(list.get(i)));
		}
		return tmp;
	}
	
	 public List<String[]> init() {
	        List<String[]> segList = new ArrayList<String[]>();
	        String[] str1 = { "12岁", "女生", "宿舍", "表演", "上吊", "身亡", "室友", "错过", "2次", "施救", "机会" };
	        String[] str2 = { "12岁", "女生", "宿舍", "内", "上吊", "室友", "以为", "玩笑", "错失", "施救", "机会" };
	        String[] str3 = { "12岁", "住校", "女生", "宿舍", "身亡" };
	        String[] str4 = { "陕西", "手机", "早报", "0402" };
	        String[] str5 = { "四川", "通报", "小学", "女生", "死亡", "事件", "系", "意外", "排除", "他", "杀" };
	        String[] str6 = { "陕西", "手机", "早报", "0402" };
	        String[] str7 = { "12岁", "住校", "女生", "宿舍", "身亡" };
	        String[] str8 = { "12岁", "女生", "宿舍", "内", "上吊", "室友", "以为", "玩笑", "错失", "施救", "机会" };
	        segList.add(str8);
	        segList.add(str8);
	        segList.add(str8);
	        segList.add(str8);
	        segList.add(str8);
	        segList.add(str7);
	        segList.add(str6);
	        segList.add(str5);
	        segList.add(str5);
	        segList.add(str5);
	        segList.add(str5);
	        segList.add(str5);
	        segList.add(str4);
	        segList.add(str3);
	        segList.add(str2);
	        segList.add(str1);
	        segList.add(str4);
	        segList.add(str4);
	        segList.add(str4);
	        segList.add(str4);
	        return segList;
	    }
	/**
	 * 
	 */
	public void initSeglist(){
		seglist = new ArrayList<String[]>();
		
		WordSegment ws = new WordSegment();
		
		String str1 = "习近平出席纪念红军长征胜利80周年大会";
		String str2 = "长征胜利80周年 习近平讲话(全文)";
		String str3 = "中国游客在日本顺走马桶盖 当事人向单位提出辞职";
		String str4 = "李克强会见菲律宾总统杜特尔特";
		String str5 = "李克强会见杜特尔特：南海问题不是中菲关系全部";
		String str6 = "最高检:凡暴力伤医案一律列为重大敏感案件";
		String str7 = "黑龙江省长在北京会见刘士余：希望得到证监会支持";
		String str8 = "赴日游客带走酒店马桶盖引热议";
		
		seglist.add(ws.parse(str8));
		seglist.add(ws.parse(str1));
		seglist.add(ws.parse(str4));
		seglist.add(ws.parse(str2));
		seglist.add(ws.parse(str1));
		seglist.add(ws.parse(str6));
		seglist.add(ws.parse(str3));
		seglist.add(ws.parse(str8));
		seglist.add(ws.parse(str1));
		seglist.add(ws.parse(str7));
		seglist.add(ws.parse(str2));
		seglist.add(ws.parse(str5));
		seglist.add(ws.parse(str3));
		seglist.add(ws.parse(str6));
		seglist.add(ws.parse(str4));
	}
	
	/**
	 * 
	 */
	public void showSeglist(){
		for(String[] s:seglist){
			for(String str:s){
				System.out.print(str+" ");
			}
			System.out.println();
		}
	}
	
	public void showVectors(){
		for(double[] v : vectors ){
			for(double d :v){
				System.out.print(String.format("%4.2f", d)+" ");
			}
			System.out.println();
		}
	}
	/**
	 * 
	 */
	public void showResult(){
		System.out.println("resultIndex size:"+resultIndex.size());
		for(int i = 0 ; i < resultIndex.size() ; i++){
			List<Integer> tmpIndex = resultIndex.get(i);
			for(int j = 0 ; j < tmpIndex.size() ; j++){
				String[] s = seglist.get(tmpIndex.get(j));
				for(String str : s){
					System.out.print(str);
				}
				System.out.println();
//				System.out.print(tmpIndex.get(j)+" ");
			}
			System.out.println();			
		}
	}
}
