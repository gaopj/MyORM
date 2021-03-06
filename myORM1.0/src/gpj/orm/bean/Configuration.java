package gpj.orm.bean;

/**
 * 管理配置信息
 * @author gpj
 *
 */
public class Configuration {

	/**
	 * 
	 * @param driver 驱动类
	 * @param url	jdbcURL
	 * @param user	数据库用户名字
	 * @param pwd	数据库密码
	 * @param usingDB	正在使用哪个数据库
	 * @param srcPath	项目的源码路径
	 * @param poPackage	扫描生成java类包（po：Persistence object 对象）
	 * @param queryClass 项目使用的查询类的路径
	 * @param poolMinSize 连接池中最小连接数
	 * @param poolMaxSize 连接池中最大连接数	
	*/

	public Configuration() {
	}

	public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
			String poPackage, String queryClass, int poolMinSize, int poolMaxSize) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
		this.queryClass = queryClass;
		this.poolMinSize = poolMinSize;
		this.poolMaxSize = poolMaxSize;
	}

	private String driver;
	private String url;
	private	String user;
	private	String pwd;
	private	String usingDB;
	private	String srcPath;
	private	String poPackage;
	private String queryClass;
	private int poolMinSize;
	private int poolMaxSize;
	
	public int getPoolMinSize() {
		return poolMinSize;
	}

	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}

	public int getPoolMaxSize() {
		return poolMaxSize;
	}

	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}

	public String getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsingDB() {
		return usingDB;
	}
	public void setUsingDB(String usingDB) {
		this.usingDB = usingDB;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getPoPackage() {
		return poPackage;
	}
	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}
	@Override
	public String toString() {
		return "Configuration [driver=" + driver + ", url=" + url + ", user=" + user + ", pwd=" + pwd + ", usingDB="
				+ usingDB + ", srcPath=" + srcPath + ", poPackage=" + poPackage + "]";
	}


}
