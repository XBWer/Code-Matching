/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package preTreatment;

/**
 * @Title:停用词处理
 * @Description:判断是否为停用词
 * @Author:XBW
 * @Date:2015年1月26日
 */
public class StopWordsHandler {

	private static String stopWordsList[] = { " ", "", "public", "static",
			"Exception", "try", "catch", "void", "int", "private", "if",
			"printstacktrace", "new", "String", "boolean", "java", "null",
			"double", "float", "throw", "final", "import" };// 常用停用词

	public static boolean IsStopWord(String word) {
		for (int i = 0; i < stopWordsList.length; ++i) {
			if (word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}
}
