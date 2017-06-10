package gpj.orm.core;

/**
 * 负责java数据类型和数据库数据类型相互转换
 * @author gpj
 *
 */
public interface TypeConvertor {

	/**
	 * 将数据库数据类型转化成java的数据类型
	 * @param columnType
	 * @return java的数据类型
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * 负责将java数据类型转化成数据库类型
	 * @param javaDataType
	 * @return 数据库类型
	 */
	public String javaType2DatabaseType(String javaDataType);
}
