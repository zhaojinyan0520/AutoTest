package com.youceedu.inter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFindlerUtil {
	/**
	 * ���������õ����Ŀ¼������Ŀ¼�µ�������
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
	 * ͨ���ļ�����ȡ�ļ�
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
