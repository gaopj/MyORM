package gpj.orm.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gpj.orm.core.DBManager;

/**
 * 连接池类
 * 
 * @author gpj
 *
 */
public class DBConnPool {

	public DBConnPool() {
		super();
		initPool();
	}

	/**
	 * 连接池对象
	 */
	private  List<Connection> pool;// 连接池对象

	/**
	 * 最大连接数
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();

	/**
	 * 最小连接数
	 */
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
	

	/**
	 * 初始化连接池，池中的连接数达到最小值
	 */
	public void initPool() {
		if (pool == null) {
			pool = new ArrayList<Connection>();
		}
		while (pool.size() < DBConnPool.POOL_MIN_SIZE) {
			pool.add(DBManager.createConn());
			System.out.println("初始化池中连接数:" + pool.size());
		}
	}

	/**
	 * 从连接池中取出连接
	 * 
	 * @return
	 */
	public synchronized Connection getConnection() {
		int last_index = pool.size() - 1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}

	/**
	 * 从连接池中放回一个连接
	 * 
	 * @return
	 */
	public synchronized void close(Connection conn) {
		if (pool.size() >= POOL_MAX_SIZE) {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			pool.add(conn);
		}
	}
}
