package et.common.utils;

/**
 * 字符串处理工具包
 * @author 李雅翔
 * @date 2018年1月2日
 */
public class StringProcessingUtils {
	
	/**
	 * 截取指定行的字符串
	 * @param string 带有换行符的字符串
	 * @param line		截取行数
	 * @return
	 */
	public static String subStringLine(String string, int line) {
		int fromIndex = 0;
		if (line == 0) {
			return string.substring(0,string.length());
		}
		for (int i = 0; i < line; i++) {
			int index = string.indexOf("\n", fromIndex);
			if (index == -1) {
				fromIndex = string.length()+1;
				break;
			}
			fromIndex = index + 1;
		}
		return string.substring(0,fromIndex-1);
	}
}
