/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package traningSetPreTreatment;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Title:
 * @Description:把训练的样本进行数字转换，一遍调用spmf进行序列挖掘
 * @Author:XBW
 * @Date:2015年1月27日
 */
public class Change {

	public final static String[] table = { "statement", "preparedstatement",
			"resultset", "getconn", "preparestatement", "executequery",
			"close", "from", "where", "sqlexception", "insert",
			"executeupdate",
			// --------------------SQL-------------
			"file", "fileinputstream", "read", "ioexception", "write",
			"reader", "bufferedreader","readline"
			// -------------------FILE-------------
	};

	public static List<int[]> change_2_num(List<String[]> strl) {
		List<int[]> seqnum = new ArrayList<int[]>();
		for (String[] seq : strl) {
			Vector seqnumtmp = new Vector();

			int len = seq.length;
			int cnt = 0;
			for (int i = 0; i < len; i++) {
				int index = java.util.Arrays.asList(table).indexOf(seq[i]);
				if (index != -1) {
					seqnumtmp.add(index);
					seqnumtmp.add(-1);
				}
			}
			seqnumtmp.add(-2);
			int[] seql = new int[seqnumtmp.size()];
			for (int i = 0; i < seqnumtmp.size(); i++) {
				seql[i] = (Integer) seqnumtmp.get(i);
			}
			seqnum.add(seql);
		}
		return seqnum;
	}

}
