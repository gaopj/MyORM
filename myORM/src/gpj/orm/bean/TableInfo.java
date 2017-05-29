package gpj.orm.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gpj.orm.bean.ColumnInfo;



/**
 * 存储表结构的信息
 * @author gpj
 *
 */
public class TableInfo {

	
	public TableInfo() {
		super();
	}



	public TableInfo(String tname, List<ColumnInfo> priKeys ,Map<String, ColumnInfo> colums) {
		super();
		this.tname = tname;
		this.colums = colums;
		this.priKeys = priKeys;
	}

	

	public TableInfo(String tname, Map<String, ColumnInfo> colums, ColumnInfo onlyPriKey) {
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
	private Map<String,ColumnInfo> colums;
	
	/**
	 * 唯一主键
	 */
	private ColumnInfo onlyPriKey;
	
	/**
	 * 联合主键
	 */
	private List<ColumnInfo> priKeys;

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Map<String, ColumnInfo> getColums() {
		return colums;
	}

	public void setColums(Map<String, ColumnInfo> colums) {
		this.colums = colums;
	}

	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}

	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}

	public List<ColumnInfo> getPriKeys() {
		return priKeys;
	}

	public void setPriKeys(List<ColumnInfo> priKeys) {
		this.priKeys = priKeys;
	}
	
	
}
