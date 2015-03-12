/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package classifyBayes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import preTreatment.InitSeqBase;
import preTreatment.InitTestfile;

/**
 * @Title:对新的文件进行分类
 * @Description:
 * @Author:XBW
 * @Date:2015年1月26日
 */
public class finalClassify {

	public static String[] testfileseq;
	public static String[] classlist; // 类别列表

	public static List<ArrayList<String[]>> seqbase = new ArrayList<ArrayList<String[]>>(); // 挖掘得到的序列列表

	/**
	 * 
	 * @param classnum
	 * @param testseq
	 * @return 测试序列属于第i类的概率
	 * @Description:测试序列属于第i类的概率
	 */
	public static double similiar2class(int classnum, String[] testseq,
			List<ArrayList<String[]>> sbase) {
		double sim = 0.0;
		int totalclass = sbase.get(classnum).size();
		for (int i = 0; i < totalclass; i++) {
			sim += (similiar_seq2seq(testseq, sbase.get(classnum).get(i)));
		}
		return sim / ((double) totalclass);
	}

	/**
	 * 
	 * @param testseq
	 * @param seqbase
	 * @return
	 * @Description:返回两个序列之间的概率[0,1]
	 */
	public static double similiar_seq2seq(String[] testseq, String[] seqbase) {
		int testseqlen = testseq.length;
		int seqbaselen = seqbase.length;
		int testindex, baseindex;
		for (testindex = 0, baseindex = 0; testindex < testseqlen; testindex++) {
			if (testseq[testindex].equals(seqbase[baseindex])) {
				baseindex++;
				if (baseindex == seqbaselen) {
					return 1.0;
				}
			}
		}
		return ((double) baseindex) / ((double) seqbaselen);
	}

	public static void finalclassify(String testfileDirpath) {
		File filedir = new File(testfileDirpath);
		System.out.println("请设定阈值：");
		Scanner cin = new Scanner(System.in);
		double setsim = cin.nextDouble();
		for (File f : filedir.listFiles()) {
			InitSeqBase seqb = new InitSeqBase();
			seqbase = seqb.InitSeqBase();
			classlist = seqb.Classlist;

			testfileseq = InitTestfile.InitTestfile(f.getPath());

			int classnum = 0;
			String maxtype = null;
			double maxsim = -1;
			for (String clas : classlist) {
//				System.out.println("测试文件"+f.getName()+"在类别" + clas + "中的相似概率为");
				double tmp = similiar2class(classnum, testfileseq, seqbase);
//				System.out.println(tmp);
				if (tmp > maxsim) {
					maxsim = tmp;
					maxtype = clas;
				}
				classnum++;
			}
			System.out.println("该测试文件"+f.getName()+"被判为");
			if (maxsim >= setsim) {
				System.out.println(maxtype);
			} else {
				System.out.println("无法判断！");
			}
		}
	}
}
