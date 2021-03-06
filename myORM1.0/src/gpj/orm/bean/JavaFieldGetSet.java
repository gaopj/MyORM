package gpj.orm.bean;


/**
 * 封装了java属性和getset方法的源代码
 * @author gpj
 *
 */
public class JavaFieldGetSet {

	
	
	public JavaFieldGetSet() {

	}

	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		this.fieldInfo = fieldInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	}

	/**
	 * 属性的源码信息。如：private int userId
	 */
	private String fieldInfo;
	
	/**
	 * get方法源码信息，如public int getUserId(){} 
	 */
	private String getInfo;
	
	/**
	 * set方法源码信息，如public int setUserId(int id){this.id = id;} 
	 */
	private String setInfo;

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	public String getGetInfo() {
		return getInfo;
	}

	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}

	public String getSetInfo() {
		return setInfo;
	}

	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}

	@Override
	public String toString() {
		System.out.println(fieldInfo);
		System.out.println(getInfo);
		System.out.println(setInfo);
		return super.toString();
	}
	
	
}
