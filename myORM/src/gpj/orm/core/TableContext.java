package gpj.orm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gpj.orm.bean.ColumnInfo;
import gpj.orm.bean.TableInfo;
import gpj.orm.utils.JavaFileUtils;
import gpj.orm.utils.StringUtils;

/**
 * 负责管理数据库多有表结构和类结构的关系，并可以表根据结构生成类机构
 * @author gpj
 *
 */
public class TableContext {

	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String,TableInfo> tables = new HashMap<String,TableInfo>();
	
	/**
	 * 将po的class对象和表信息关联起来。便于重用
	 */
	public static Map<Class,TableInfo> poClassTableMap = new HashMap<Class,TableInfo>();
	
	private TableContext(){}
	
	static{
		try {
			
			//初始化获得表的信息
			Connection con = DBManager.getMysqlConn();
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet tableRet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
			
			while(tableRet.next()){
				String tableName = (String)tableRet.getObject("TABLE_NAME");
				
				TableInfo ti = new TableInfo(tableName,new ArrayList<ColumnInfo>(),new HashMap<String,ColumnInfo>());
				tables.put(tableName, ti);
				
				ResultSet set  = dbmd.getColumns(null, "%", tableName, "%");//查询表中所有字段
				while(set.next()){
					ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);
					ti.getColums().put(set.getString("COLUMN_NAME"), ci); 
				}
				
				ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);//查询表中主键
				while(set2.next()){
					ColumnInfo ci2 = (ColumnInfo)ti.getColums().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);//设置为主键
					ti.getPriKeys().add(ci2);
				}
				
				if(ti.getPriKeys().size()>0){//取唯一主键，方便使用，如果是联合主键则为空
					ti.setOnlyPriKey(ti.getPriKeys().get(0));
				}
			}  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//更新类机构
		updateJavaPOFile();
		
		//加载po包下所有类便于重用
		loadPOTables();

	}

	
	/**
	 * 根据表结构，更新指定的po包下面的java类
	 * 实现了从表结构转化到类结构
	 */
	public static void updateJavaPOFile(){
		Map<String,TableInfo> map= TableContext.tables;	
		for(TableInfo t:map.values()){
			JavaFileUtils.createJavaPOFile(t,new MySqlTypeConvertor());
		}
	}
	
	public static Map<String,TableInfo> getTableInfos(){
		return tables;
	}
	
	/**
	 * 加载po包下面的类
	 */
	public static void loadPOTables(){
		for(TableInfo tableInfo:tables.values()){
			 try {
				Class c= Class.forName(DBManager.getConf().getPoPackage()+"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
				 poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}


	
	public static void main(String[] args){
		Map<String,TableInfo> tables = getTableInfos();
		//System.out.println(tables);
	}
}
