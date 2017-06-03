package gpj.orm.core;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		t.setId(2);
		
		new MySqlQuery().delete(t);
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
		//Emp.class,2==> delete from emp where id =2
		
		//通过class对象找TableInfo
		
		
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
	public int update(Object obj, String[] fielNames) {
		// TODO Auto-generated method stub
		return 0;
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