/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package preTreatment;

import java.io.File;

/**
 * @Title:
 * @Description:
 * @Author:XBW
 * @Date:2015��1��28��
 */
public class clear {

	public static String[] cleardirlist = {
	//TrainingSetNum
	"D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetNum",
	//TrainingSetAnsNum
	"D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetAnsNum",
	//class
	"D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\class"
	};

	public static void clearpre() {
		for (String str : cleardirlist) {
			delAllFile(str);
		}
	}

	/**
	 * ɾ���ļ���
	 * @param folderPath
	 * @Description:
	 */
	 public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //ɾ����������������
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //ɾ�����ļ���
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	 }
	/**
	 * ɾ��Ŀ¼�µ������ļ�
	 * 
	 * @param path
	 * @return
	 * @Description:
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
				delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
				flag = true;
			}
		}
		return flag;
	}
}
