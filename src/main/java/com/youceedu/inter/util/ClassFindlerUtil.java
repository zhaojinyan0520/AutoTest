package com.youceedu.inter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFindlerUtil {
	/**
	 * 根据类名得到类的目录或子类目录下的所有类
	 */
	
	public static List<Class> getClasses(Class<?> cls){
		List<Class> list = new ArrayList<Class>();
		try {
			
			String pk = cls.getPackage().getName();
			String path = pk.replace(".", "/");
			
			String dirPath = cls.getClassLoader().getResource(path).getPath();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
		
	}
	
	/**
	 * 通过文件名获取文件
	 */
	public static List<Class> getClasses(File file,String dir){
		List<Class>  list=new ArrayList<Class>();
		try {
			for (Class c : list) {
				if (file.isDirectory()) {
					getClasses(file, dir+"."+file.getName());
				}
				String name = file.getName();
				if (name.endsWith(".class")) {
					
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
		
	}
}
