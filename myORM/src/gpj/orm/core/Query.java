﻿package gpj.orm.core;

import java.util.List;

/**
 * 负责查询（对外提供核心类）
 * @author gpj
 *
 */

@SuppressWarnings("all")
public interface Query {

	/**
	 * 直接执行一个DML语句
	 * @param sql sql语句
	 * @param params 参数
	 * @return 执行sql语句后影响记录的行数
	 */
	int executeDML(String sql,Object[] params);
	
	
	/**
	 * 将一个对象存储到数据库中
	 * 把对象中不为null的属性往数据库中存储，如果数字为null则放0
	 * @param obj 要存储的对象
	 */
	void insert(Object obj);
	
	/**
	 * 删除clazz对象表示类对应表中记录（指定主键值id的记录）
	 * @param clazz  表对应的类的class对象
	 * @param id	主键的值
	 * @return
	 */
	void delete(Class clazz,Object id);  //delete from user where id =2;

	
	/**
	 * 删除对象在数据库中对应的记录（对象所在的类对应到表，主键的值对应到记录）
	 * @param obj
	 */
	void delete(Object obj);
	
	/**
	 * 更新对象对应的记录，并且只更新指定字段的值
	 * @param obj 所要更新的对象
	 * @param fielNames 更新的成员列表
	 * @return 影响行数
	 */
	int update(Object obj,String[] fielNames);//update user set uname=?,pwd=?
	
	/**
	 * 查询返回多行记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql  查询语句
	 * @param clazz 封装数据的javabean类的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	List queryRows(String sql,Class clazz,Object[] params);
	
	/**
	 * 查询返回一行记录，并将该行记录封装到clazz指定的类的对象中
	 * @param sql  查询语句
	 * @param clazz 封装数据的javabean类的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	Object queryUnityRows(String sql,Class clazz,Object[] params);
	
	/**
	 * 查询返回一个值（一行一列）
	 * @param sql  查询语句
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	Object queryValue(String sql, Object[] params);

	/**
	 * 查询返回一个数字（一行一列）
	 * @param sql  查询语句
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	Number queryNumber(String sql, Object[] params);
	
}
