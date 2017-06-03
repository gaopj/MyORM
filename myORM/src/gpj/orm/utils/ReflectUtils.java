package gpj.orm.utils;

import java.lang.reflect.Method;

/**
 * 封装了反射常用操作
 * 
 * @author gpj
 *
 */
public class ReflectUtils {

	/**
	 * 调用obj对象对应属性fieldName的get方法
	 * 
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName, Object obj) {

		try {
			Class c = obj.getClass();
			Method m = c.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
			return m.invoke(obj, null);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void invokeSet(Object obj,String columnName,Object columnValue) {
		Method m;
		try {
			m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName), columnValue.getClass());
			m.invoke(obj, columnValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
