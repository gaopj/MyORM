package gpj.orm.bean;

/**
 * 封装了表中一个字段的信息
 * @author gpj
 *
 */
public class ColumInfo {

	/**
	 * 字段名称
	 */
	private String name;
	
	/**
	 * 字段数据类型
	 */
	private String dataType;
	

	/**
	 * 字段键数据类型 0普通键  1主键 2外键
	 */
	private int keyType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

}
