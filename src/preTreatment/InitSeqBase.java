/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package preTreatment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Title:
 * @Description:
 * @Author:XBW
 * @Date:2015年1月26日
 */
public class InitSeqBase {

	public static String basepath = "D:\\InnovationClub\\201501\\相似代码匹配\\Project\\CodeSimilar_2015.01.28_V3\\class";
	public static String[] Classlist; // 类别列表
	/**
	 * 
	 * @param filepath
	 * @return
	 * @Description:读取指定路径的文件序列库
	 */
	public static List<ArrayList<String[]>> InitSeqBase() {
		File SeqBasepath = new File(basepath);
		Classlist = SeqBasepath.list();
		File[] filelist = SeqBasepath.listFiles();
		List<ArrayList<String[]>> SeqBase = new ArrayList<ArrayList<String[]>>();

		// 遍历类别，一个类别一个文件
		for (int i = 0; i < filelist.length; i++) {
			ArrayList<String[]> classseqbase=new ArrayList<String[]>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						filelist[i]));
				// 开始读文件
				String Line = null;
				while ((Line = reader.readLine()) != null) {
					String[] ls = Line.split(" ");
					classseqbase.add(ls);
				}
				SeqBase.add(classseqbase);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return SeqBase;
	}

	// public static void main(String[] args){
	// InitSeqBase();
	// }
}
