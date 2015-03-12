/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package runprocess;

import preTreatment.clear;
import classifyBayes.finalClassify;
import sequenceMining.SeqMining;
import traningSetPreTreatment.TraningSetPreTreatment;

/**
 *@Title:运行整个程序
 *@Description:运行整个程序
 *@Author:XBW
 *@Date:2015年1月27日
 */
public class RunProcess {

	private static String testfileDir = "D:\\InnovationClub\\201501\\相似代码匹配\\Project\\CodeSimilar_2015.01.28_V3\\testcase";
	
	public static void main(String[] args){
		
		clear.clearpre();
		System.out.println("程序初始化完毕！");
		
		//训练集预处理——TraningSetPreTreatment
		//全部转换为数字
		//输入：TrainingSetCharacter
		//输出：TrainingSetNum
		TraningSetPreTreatment.traningsetpretreatment();
		System.out.println("训练集预处理完毕！");
		
		//序列挖掘——SeqMining
		//输入：TrainingSetNum
		//输出：class
		SeqMining.mining();
		System.out.println("序列挖掘完毕！");
		
		//分类——finalClassify
		//训练集：class
		//待测:
		finalClassify.finalclassify(testfileDir);
		System.out.println("训练集预处理完毕！");
		
		System.out.println("程序执行完毕！");
	}
}
