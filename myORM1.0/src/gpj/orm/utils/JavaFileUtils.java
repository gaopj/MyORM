package gpj.orm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gpj.orm.bean.ColumnInfo;
import gpj.orm.bean.JavaFieldGetSet;
import gpj.orm.bean.TableInfo;
import gpj.orm.core.DBManager;
import gpj.orm.core.MySqlTypeConvertor;
import gpj.orm.core.TableContext;
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

	/**
	 * 根据表信息生成java类的源代码
	 * @param tableInfo 表信息
	 * @param convertor	数据类型转化器
	 * @return java类的源代码
	 */
	public static String createJavaSrc(TableInfo tableInfo,TypeConvertor convertor){
		StringBuilder src = new StringBuilder();
		
		Map<String,ColumnInfo> columns = tableInfo.getColums();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		
		for(ColumnInfo c:columns.values()){
			javaFields.add(createFieldGetSetSRC(c,convertor));
		}
		
		//生成package语句
		src.append("package " +DBManager.getConf().getPoPackage()+";\n\n");
		
		//生成import语句
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		
		//生成类声明语句
		src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+" {\n\n");
		
		//生成属性列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		
		//生成get方法列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getGetInfo());
		}
		//生成set方法列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getSetInfo());
		}
		
		//生成结束语句
		src.append("}\n");
		
		return src.toString();
	}
	
	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor){
		String srcPath = DBManager.getConf().getSrcPath()+"\\";
		String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
		File f = new File(srcPath+packagePath);
		if(!f.exists()){
			f.mkdirs();//目录不存在建立目录
		}
		
		BufferedWriter bw =null;
		try {
			bw = new BufferedWriter(new FileWriter(f.getAbsolutePath()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
			bw.write(createJavaSrc(tableInfo,convertor));
			System.out.println("建立表 "+tableInfo.getTname()+" 对应java类： "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
//		ColumnInfo ci = new ColumnInfo("id","int",0);
//		
//		JavaFieldGetSet f= createFieldGetSetSRC(ci,new MySqlTypeConvertor());
//		System.out.println(f);
//		
//		Map<String,TableInfo> map= TableContext.tables;	
//		TableInfo t =  map.get("t_user");	
//		createJavaPOFile(t,new MySqlTypeConvertor());
	//	TableContext.updateJavaPOFile();
	}

}
