/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package runprocess;

import preTreatment.clear;
import classifyBayes.finalClassify;
import sequenceMining.SeqMining;
import traningSetPreTreatment.TraningSetPreTreatment;

/**
 *@Title:������������
 *@Description:������������
 *@Author:XBW
 *@Date:2015��1��27��
 */
public class RunProcess {

	private static String testfileDir = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\testcase";
	
	public static void main(String[] args){
		
		clear.clearpre();
		System.out.println("�����ʼ����ϣ�");
		
		//ѵ����Ԥ������TraningSetPreTreatment
		//ȫ��ת��Ϊ����
		//���룺TrainingSetCharacter
		//�����TrainingSetNum
		TraningSetPreTreatment.traningsetpretreatment();
		System.out.println("ѵ����Ԥ������ϣ�");
		
		//�����ھ򡪡�SeqMining
		//���룺TrainingSetNum
		//�����class
		SeqMining.mining();
		System.out.println("�����ھ���ϣ�");
		
		//���ࡪ��finalClassify
		//ѵ������class
		//����:
		finalClassify.finalclassify(testfileDir);
		System.out.println("ѵ����Ԥ������ϣ�");
		
		System.out.println("����ִ����ϣ�");
	}
}
