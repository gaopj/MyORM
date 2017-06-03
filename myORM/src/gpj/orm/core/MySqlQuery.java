package gpj.orm.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import gpj.orm.bean.ColumnInfo;
import gpj.orm.bean.TableInfo;
import gpj.orm.po.T_user;
import gpj.orm.utils.JDBCUtils;
import gpj.orm.utils.ReflectUtils;
import gpj.orm.utils.StringUtils;

/**
 * 负责针对mysql数据库的查询
 * @author gpj
 *
 */
public class MySqlQuery  implements Query{

	public static void main(String[] args){
		T_user t = new T_user();
		t.setId(1);
		t.setUsername("jiazai");
		//t.setPwd("qwer1234");
	//	new MySqlQuery().delete(t);
	//	new MySqlQuery().insert(t);
		new MySqlQuery().update(t,new String[]{"username"} );
	}
	
	@Override
	public int executeDML(String sql, Object[] params) {
		Connection conn = DBManager.getMysqlConn();
		int count = 0;
		PreparedStatement ps=null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			//给sql设置参数
			JDBCUtils.handleParams(ps,params);
			
			System.out.println(ps);
			count= ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps,conn);
		}
		
		return count;
	}

	@Override
	public void insert(Object obj) {
		//obj-->表中， insert into 表名 (id,uname,pew) values (?,?,?)
		Class c  = obj.getClass();
		List<Object> params = new ArrayList<Object>();//存储sql的参数对象
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		StringBuilder sql = new StringBuilder("insert into "+tableInfo.getTname()+" (");
		int countNotNullField = 0;//计算不为null的属性值
		Field[] fs = c.getDeclaredFields();
		
		for(Field f:fs){
			String fieldName = f.getName();
			Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
			
			if(fieldValue!=null){
				sql.append(fieldName+",");
				countNotNullField++;
				params.add(fieldValue);
			}
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values (");
		
		for(int i = 0;i<countNotNullField;i++){
			sql.append("?,");	
		}
		sql.setCharAt(sql.length()-1, ')');
		
		executeDML(sql.toString(),params.toArray());
	}

	@Override
	public void delete(Class clazz, Object id) {
		//通过Class对象找TableInfo
		TableInfo tableInfo= TableContext.poClassTableMap.get(clazz);
		
		//获得主键
		ColumnInfo onlyPriKey =   tableInfo.getOnlyPriKey();
		
		String sql = "delete from "+tableInfo.getTname()+" where "+onlyPriKey.getName()+"=? ";
		
		executeDML(sql,  new Object[]{id});
	}

	@Override
	public void delete(Object obj) {
		Class c =obj.getClass();
		TableInfo tableInfo= TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey =   tableInfo.getOnlyPriKey();//获得主键
		
		//通过反射机制，调用属性对应的get和set		
		Object priKeyValue= ReflectUtils.invokeGet(onlyPriKey.getName(), obj);
		delete(c, priKeyValue);
	}

	@Override
	public int update(Object obj, String[] fieldNames) {
		// obj {"uname","pwd"}--> update 表名  set uname = ?,pwd=? where id =?
		Class c  = obj.getClass();
		List<Object> params = new ArrayList<Object>();//存储sql的参数对象
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		ColumnInfo priKey = tableInfo.getOnlyPriKey();
		StringBuilder sql = new StringBuilder("update "+tableInfo.getTname()+" set ");
		
		for(String fname:fieldNames){
			Object fvalue = ReflectUtils.invokeGet(fname,obj);
			params.add(fvalue);
			sql.append(fname+"=?,");
		}
		sql.setCharAt(sql.length()-1, ' ');
		sql.append(" where ");
		sql.append(priKey.getName()+"=? ");
		
		params.add(ReflectUtils.invokeGet(priKey.getName(), obj));// 主键的值
		return executeDML(sql.toString(),params.toArray());
	}

	@Override
	public List queryRows(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object queryUnityRows(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object queryValue(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number queryNumber(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

}
