package gpj.orm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

import gpj.orm.bean.Configuration;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author gpj
 *
 */
public class DBManager {

	private static Configuration conf;
	static {//静态代码块 类初始化时执行
		Properties pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		conf = new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setUser(pros.getProperty("user"));
		conf.setUrl(pros.getProperty("url"));
		
		System.out.println(conf.toString());
	}
	
	public static Connection getMysqlConn(){
		try {//直接建立连接,后期增加连接池处理提高效率
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

	}
	
	public static void close(ResultSet rs,Statement ps, Connection conn){

			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	
	public static void close(Statement ps, Connection conn){

		try {
	
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

}
	
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回configuration对象
	 * @return
	 */
	public static Configuration getConf(){
		return conf;
	}
}
