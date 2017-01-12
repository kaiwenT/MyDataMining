package com.hust.dm.test;
import java.util.ArrayList;
import java.util.List;

import com.hust.dm.util.CosSimilarity;
import com.hust.dm.util.TFIDFConverter;
import com.hust.dm.util.Text2List;
import com.hust.dm.util.Threshold;
import com.hust.dm.util.WordSegment;

import org.junit.Test;

public class CanopyTest {

	//ԭʼ�ı�
	private List<String> datalist;
	//�ִʺ���ı�
	private List<String[]> seglist;
	
	//ԭʼ�ı���Ӧ����
	private List<double[]> vectors;
	
	//��������Ӧ���±�
	private List<List<Integer>> resultIndex;
	//
//	private List<List<double[]>> resultVector;
	
	/**
	 * �ִ�
	 */
	@Test
	public void wordSegment(){
		String str = "���ǿ������ض����Ϻ����ⲻ���зƹ�ϵȫ��";
//		String str = "����Ͱ���2017��12��16���ڸ����к�Ͽ���ʻ������ľٰ조������̷�չ�߷���̳��2017���������������ʽ��������������̷�չ��̳���ҹ����������������������ƽ̨��Ҳ�ǰ���Ͱͼ�����ũ���������չ��һ�μ��г��֡�";
//		String str = "360��˾����Ϥ���ˡ������ؼҡ���2017�괺�˴����ݷ����ᡱ��ʽ�ھ��ٿ���360Я��Я�̡�ȥ�Ķ������ڵ������������εȺ�������Լ�ȫ������ý�干ͬ��ϯ�˱��λ����ֳ���360����������ˡ�2017�괺��Ԥ�ⱨ�桷��ͬʱ��ʽ�����ˡ�360�ֻ��������Ʊר�桱�͡����˴����ݿ��ӻ���ͼϵͳ�������ڴ����ݶԴ������ƽ��������Ρ�ȫ��λ�Ľ��";
		String[] words = new WordSegment().parse(str);
		for(String s :words){
			System.out.print(s+" / ");
		}
	}
	
	@Test
	public void canopy(){
		//��ʼ��ԭʼ�ı�  + �ִ�
//		initSeglist();
		/**
		���ļ��ж�ȡ�ı������浽List��
		**/
		Text2List t2l = new Text2List();
		t2l.read();
		datalist = t2l.getDataList();
		seglist = t2l.getSegList();
		 
//		seglist = init();
		//��ʼ��canopy��ֵ
		double threshold = 0.16f;
		//��ʾ�ִʵ�ԭʼ�ı�
		showSeglist();
		
		//����ת��
		TFIDFConverter converter = new TFIDFConverter();
		vectors = converter.convert(seglist);
		
		//������ֵ
		double thre = new Threshold().getThreshold(vectors);
		System.out.println("�������ֵ��"+thre);
		threshold = thre;
		//
		showVectors();
		//canopy�㷨
		resultIndex = new ArrayList<List<Integer>>();
		List<Integer> tmpIndex = null;
		for(int i = 0 ; i < vectors.size() ; i++){
			double[] vector = vectors.get(i);
			// = new ArrayList<Integer>();
			//i = 0 һ���඼û��ʱ��ֱ����ӽ�resultIndex��
			if(i == 0){
//				List<Integer> tmpIndex = resultIndex.get(i);
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
				continue;
			}
			
			//�ҵ��������ƶ�Ҫ�����ı�־
			boolean isFind = false;
			
			for(int j = 0 ; j < resultIndex.size() ; j++){
				//�õ���j�����������
				List<double[]> tmpVectors = getTmpVector(resultIndex.get(j));
				CosSimilarity sim = new CosSimilarity();
//				System.out.println("sim:"+sim.calculate(vector,tmpVectors));
				//�����������ѷֵ��������ƽ��ֵ�Ƿ������ֵ����������ӵ�����
				if(sim.calculate(vector,tmpVectors) > threshold){
					
					tmpIndex = resultIndex.get(j);
					tmpIndex.add(i);
					//��i�ӵ�tmpIndex��Ȼ���resultIndex��ɾ����j����������tmpIndex��resultIndex
					resultIndex.remove(j);
					resultIndex.add(tmpIndex);
					isFind = true;
					break;
				}
			}
			
			//��ǰ��������ƶȶ�������Ҫ�����½�һ����
			if(!isFind){
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
			}
		}
//		System.out.println("tmpIndex.size:"+tmpIndex.size());
//		System.out.println("resultIndex.size:"+resultIndex.size());
		//��ʾ������
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
	        String[] str1 = { "12��", "Ů��", "����", "����", "�ϵ�", "����", "����", "���", "2��", "ʩ��", "����" };
	        String[] str2 = { "12��", "Ů��", "����", "��", "�ϵ�", "����", "��Ϊ", "��Ц", "��ʧ", "ʩ��", "����" };
	        String[] str3 = { "12��", "סУ", "Ů��", "����", "����" };
	        String[] str4 = { "����", "�ֻ�", "�籨", "0402" };
	        String[] str5 = { "�Ĵ�", "ͨ��", "Сѧ", "Ů��", "����", "�¼�", "ϵ", "����", "�ų�", "��", "ɱ" };
	        String[] str6 = { "����", "�ֻ�", "�籨", "0402" };
	        String[] str7 = { "12��", "סУ", "Ů��", "����", "����" };
	        String[] str8 = { "12��", "Ů��", "����", "��", "�ϵ�", "����", "��Ϊ", "��Ц", "��ʧ", "ʩ��", "����" };
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
		
		String str1 = "ϰ��ƽ��ϯ����������ʤ��80������";
		String str2 = "����ʤ��80���� ϰ��ƽ����(ȫ��)";
		String str3 = "�й��ο����ձ�˳����Ͱ��";
		String str4 = "���ǿ������ɱ���ͳ���ض�";
		String str5 = "���ǿ������ض����Ϻ����ⲻ���зƹ�ϵȫ��";
		String str6 = "��߼�:��������ҽ��һ����Ϊ�ش����а���";
		String str7 = "������ʡ���ڱ��������ʿ�ࣺϣ���õ�֤���֧��";
		String str8 = "�����οʹ��߾Ƶ���Ͱ��������";
				
		seglist.add(ws.parse(str1));
		seglist.add(ws.parse(str4));
		seglist.add(ws.parse(str5));
		seglist.add(ws.parse(str2));
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
	 * ��ʾ������
	 */
	public void showResult(){
		System.out.println("Canopy����:"+resultIndex.size());
		for(int i = 0 ; i < resultIndex.size() ; i++){
			List<Integer> tmpIndex = resultIndex.get(i);
			for(int j = 0 ; j < tmpIndex.size() ; j++){
				System.out.println(datalist.get(tmpIndex.get(j)));
			}
			System.out.println();			
		}
	}
}
