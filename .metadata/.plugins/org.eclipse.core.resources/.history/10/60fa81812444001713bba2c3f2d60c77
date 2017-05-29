package gpj.orm.bean;

import java.util.Map;

/**
 * 存储表结构的信息
 * @author gpj
 *
 */
public class TableInfo {

	
	public TableInfo() {
		super();
	}

	public TableInfo(String tname, Map<String, ColumInfo> colums, ColumInfo onlyPriKey) {
		super();
		this.tname = tname;
		this.colums = colums;
		this.onlyPriKey = onlyPriKey;
	}

	/**
	 * 表名
	 */
	private String tname;
	
	/**
	 * 所有字段信息
	 */
	private Map<String,ColumInfo> colums;
	
	/**
	 * 唯一主键（目前只能处理有且只有一个主键情况）
	 */
	private ColumInfo onlyPriKey;

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Map<String, ColumInfo> getColums() {
		return colums;
	}

	public void setColums(Map<String, ColumInfo> colums) {
		this.colums = colums;
	}

	public ColumInfo getOnlyPriKey() {
		return onlyPriKey;
	}

	public void setOnlyPriKey(ColumInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}
	
	
}
