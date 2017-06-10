package gpj.orm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装了JDBC查询常用操作
 * @author gpj
 *
 */
public class JDBCUtils {

	/**
	 * //给sql设置参数
	 * @param ps 预编译sql对象
	 * @param obj
	 */
	public static void handleParams(PreparedStatement ps,Object[] params){
		
		if(params!=null){
			for(int i=0;i<params.length;i++){
				try {
					ps.setObject(1+i, params[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
