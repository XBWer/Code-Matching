/**
 *@Copyright:Copyright (c) 2008 - 2015 *
 */
package preTreatment;

/**
 * @Title:ͣ�ôʴ���
 * @Description:�ж��Ƿ�Ϊͣ�ô�
 * @Author:XBW
 * @Date:2015��1��26��
 */
public class StopWordsHandler {

	private static String stopWordsList[] = { " ", "", "public", "static",
			"Exception", "try", "catch", "void", "int", "private", "if",
			"printstacktrace", "new", "String", "boolean", "java", "null",
			"double", "float", "throw", "final", "import" };// ����ͣ�ô�

	public static boolean IsStopWord(String word) {
		for (int i = 0; i < stopWordsList.length; ++i) {
			if (word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}
}
