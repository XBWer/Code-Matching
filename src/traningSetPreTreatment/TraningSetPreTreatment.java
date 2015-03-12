/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package traningSetPreTreatment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import preTreatment.InitTestfile;

/**
 * @Title:
 * @Description:对训练集进行预处理
 * @Author:XBW
 * @Date:2015年1月27日
 */
public class TraningSetPreTreatment {

	public static String TraningSetCharacterPath = "D:\\InnovationClub\\201501\\相似代码匹配\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetCharacter";
	public static String TraningSetNumPath = "D:\\InnovationClub\\201501\\相似代码匹配\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetNum";
	public static String[] Classlist; // 类别列表

	/**
	 * 
	 * @param f
	 * @Description:对于指定类别进行预处理
	 */
	public static List<String[]> trainingsetpretreatment(File f) {
		List<String[]> trainingchar = new ArrayList<String[]>();
		File[] filelist = f.listFiles();
		for (File fl : filelist) {
			trainingchar.add(InitTestfile.InitTestfile(fl.toString()));
		}
		return trainingchar;
	}
	
	public static void traningsetpretreatment() {
		File traningsetpath = new File(TraningSetCharacterPath);
		Classlist = traningsetpath.list();
		File[] filelist = traningsetpath.listFiles();

		// 遍历所有类别
		for (File f : filelist) {

			// 在TrainingSetNum目录下生成txt文件
			File newf = new File(TraningSetNumPath, f.getName() + ".txt");
			try {
				newf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 对特定一类的字符训练集进行预处理
			List<String[]> traningchar = trainingsetpretreatment(f);
			List<int[]> trainingnum = Change.change_2_num(traningchar);

			// 写入TraningSetNumPath
			FileWriter fw = null;
			BufferedWriter writer = null;
			try {
				fw = new FileWriter(newf);
				writer = new BufferedWriter(fw);
				for (int[] seqint : trainingnum) {
					writer.write(seqint[0] + "");
					for (int i = 1; i < seqint.length; i++) {
						writer.write(" ");
						writer.write(seqint[i] + "");
					}
					writer.newLine();// 换行
				}
				writer.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
