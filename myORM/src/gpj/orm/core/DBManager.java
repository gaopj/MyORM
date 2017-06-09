package gpj.orm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import gpj.orm.bean.Configuration;
import gpj.orm.pool.DBConnPool;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * 
 * @author gpj
 *
 */
public class DBManager {

	/**
	 * 配置信息
	 */
	private static Configuration conf;
	
	/**
	 * 连接池对象
	 */
	private static DBConnPool pool ;
	static {// 静态代码块 类初始化时执行
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
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));

		//System.out.println(conf.toString());
		System.out.println(TableContext.class);//加载TableContext对象
	}

	/**
	 * 获得Connection对象
	 * 
	 * @return
	 */
	public static Connection getMysqlConn() {
		if(pool==null){
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}

	/**
	 * 创建新的Connection
	 * 
	 * @return
	 */
	public static Connection createConn() {
		try {// 直接建立连接,后期增加连接池处理提高效率
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 关闭传入的对象
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement ps, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				//conn.close();
				pool.close(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 关闭传入的对象
	 * 
	 * @param ps
	 * @param conn
	 */
	public static void close(Statement ps, Connection conn) {

		try {

			if (ps != null) {
				ps.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
			pool.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 关闭传入的对象
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			pool.close(conn);
		}
	}

	/**
	 * 返回configuration对象
	 * 
	 * @return
	 */
	public static Configuration getConf() {
		return conf;
	}
}
