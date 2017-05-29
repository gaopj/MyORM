package gpj.orm.utils;

import gpj.orm.bean.ColumnInfo;
import gpj.orm.bean.JavaFieldGetSet;
import gpj.orm.core.MySqlTypeConvertor;
import gpj.orm.core.TypeConvertor;

/**
 * 封装了生成Java文件（源代码）常用操作
 * @author gpj
 *
 */
public class JavaFileUtils {
	/**
	 * 根据字段信息生成java属性信息 ，如：var username --> private String username;以及相应的get，set方法
	 * @param column 字段信息
	 * @param convertor 类型转化器
	 * @return java属性和set/get方法源码
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor){
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		
		String javaFieldType =convertor.databaseType2JavaType(column.getDataType());
		jfgs.setFieldInfo("\tprivate "+javaFieldType +" "+column.getName() +";\n");
		
		//public String getUsername(){return username;}
		//生成get源码
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic "+javaFieldType +" get"+StringUtils.firstChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\t\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		
		//public String setUsername(String username){this.username = username;}
		//生成set源码
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set"+StringUtils.firstChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType + " "+column.getName()+"){\n");
		setSrc.append("\t\t this. "+column.getName()+" = "+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		
		
		return jfgs;
	}
	
	public static void main(String[] args){
		ColumnInfo ci = new ColumnInfo("id","int",0);
		
		JavaFieldGetSet f= createFieldGetSetSRC(ci,new MySqlTypeConvertor());
		System.out.println(f);
	}

}
