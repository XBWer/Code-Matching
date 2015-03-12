/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package sequenceMining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import traningSetPreTreatment.Change;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoMaxSP;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import ca.pfv.spmf.test.MainTestMaxSP_saveToFile;

/**
 * @Title:
 * @Description:��ѵ��������Ԥ���������spmf������������ھ�
 * @Author:XBW
 * @Date:2015��1��27��
 */
public class SeqMining {

	public static String seqminingpath = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetNum";

	public static String outputpath = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetAnsNum";

	public static String trainingsetansnum = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetAnsNum";

	public static String classlist = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\TrainingSetCharacter";

	public static String classans = "D:\\InnovationClub\\201501\\���ƴ���ƥ��\\Project\\CodeSimilar_2015.01.28_V3\\class";

	public static void mining() {
		File flist = new File(seqminingpath);

		for (File f : flist.listFiles()) {

			// Load a sequence database
			try {
				SequenceDatabase sequenceDatabase = new SequenceDatabase();
				sequenceDatabase.loadFile(f.getAbsolutePath());
				// sequenceDatabase.print();

				FileReader fr = new FileReader(f.getAbsolutePath());
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				double minsup = 0.0;
				while ((line = br.readLine()) != null) {
					minsup += 1.0;
				}
				br.close();
				fr.close();
				minsup *= 0.5; // we use a minsup of 2 sequences (50 % of the
								// database
								// size)

				AlgoMaxSP algo = new AlgoMaxSP();

				// execute the algorithm
				algo.runAlgorithm(sequenceDatabase,
						outputpath + "\\" + f.getName(), (int) minsup);
				algo.printStatistics(sequenceDatabase.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		change_2_char();
	}

	public static String fileToPath(String filename)
			throws UnsupportedEncodingException {
		URL url = MainTestMaxSP_saveToFile.class.getResource(filename);
		return java.net.URLDecoder.decode(url.getPath(), "UTF-8");
	}

	/**
	 * 
	 * 
	 * @Description:ת��Ϊ�ַ��ͣ����ڷ���
	 */
	public static void change_2_char() {

		File traningsetpath = new File(classlist);
		String[] Classlist = traningsetpath.list();

		List<ArrayList<String[]>> trainingchar = new ArrayList<ArrayList<String[]>>();
		List<ArrayList<int[]>> trainingnum = new ArrayList<ArrayList<int[]>>();

		// ------------------��ȡ-------------------------------
		File newfl = new File(trainingsetansnum);
		int cnt = 0;
		for (File f : newfl.listFiles()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(f));
				ArrayList<String[]> tmp = new ArrayList<String[]>();
				String Line = null;
				while ((Line = reader.readLine()) != null) {
					String[] ls = Line.split(" ");
					int len = ls.length;
					String[] tmps = new String[(len - 3) / 2];

					int count = 0;
					for (int i = 0; i < len - 3; i += 2) {
						tmps[count++] = Change.table[Integer.parseInt(ls[i])];
					}
					tmp.add(tmps);
				}
				trainingchar.add(tmp);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnt++;
		}

		// ------------------д��--------------------------------
		// �����������
		int type = 0;
		for (String f : Classlist) {

			// ��classĿ¼������txt�ļ�
			File newf = new File(classans, f + ".txt");
			try {
				newf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// д��TraningSetNumPath
			FileWriter fw = null;
			BufferedWriter writer = null;
			try {
				fw = new FileWriter(newf);
				writer = new BufferedWriter(fw);
				for (String[] seqint : trainingchar.get(type)) {
					for (int i = 0; i < seqint.length; i++) {
						if (i == 0) {
							writer.write(seqint[i]);
							continue;
						}
						writer.write(" " + seqint[i]);
					}
					writer.newLine();// ����
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
			type++;
		}
	}
}
