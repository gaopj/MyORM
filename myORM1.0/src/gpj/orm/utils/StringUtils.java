package gpj.orm.utils;

/**
 * 封装了字符串常用操作
 * @author gpj
 *
 */
public class StringUtils {

	/**
	 * 将目标字符串首字母变为大写
	 * @param str 目标字符串
	 * @return 首字母大写的字符串
	 */
	public static String firstChar2UpperCase(String str){
		return str.toUpperCase().substring(0,1)+str.substring(1);
	}
}
