/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package preTreatment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import traningSetPreTreatment.DeleteCom;

/**
 * @Title:
 * @Description:
 * @Author:XBW
 * @Date:2015年1月26日
 */
public class InitTestfile {

	/**
	 * 
	 * @param testfilepath
	 * @return
	 * @Description:初始化测试序列
	 */
	public static String[] InitTestfile(String testfilepath) {
		List<String> testseq = new ArrayList<String>();
		try {
			File f=new File(testfilepath);
			
			String filestr=DeleteCom.deletecom(f);//去掉注释
			String strtmp = "";
			int cnt = 0;
			for(int i=0;i<filestr.length();i++){
				char ch=filestr.charAt(i);
				if ((ch >= 'A' &&  ch <= 'Z')||(ch >= 'a' &&  ch <= 'z')) {
					strtmp +=  ch;
				} else {
					if (StopWordsHandler.IsStopWord(strtmp) == false) {
						if (strtmp.length() > 3) {
							testseq.add(strtmp.toLowerCase());
						}
						// System.out.println(strtmp);
					}
					strtmp = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] ansseq = new String[testseq.size()];
		testseq.toArray(ansseq);
		return ansseq;
	}

	// public static void main(String[] args) {
	// String[] anslist =
	// InitTestfile("D:\\InnovationClub\\201412\\相似代码匹配\\Project\\CodeSimilar\\testcase\\file_operation\\file1.txt");
	// for (String tmp : anslist) {
	// System.out.println(tmp);
	// }
	// }
}
