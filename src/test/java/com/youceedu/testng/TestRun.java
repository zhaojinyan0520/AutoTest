package com.youceedu.testng;

import org.testng.annotations.Test;

import com.youceedu.inter.util.ExcelUtil;
import com.youceedu.inter.util.HttpReqData;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestRun {
 /**
  * 定义全局变量
  * @param n
  * @param s
  */
 private String filePath = "D:\\Learing_Documents\\app_testcase1.xlsx";
	
	
  @Test(dataProvider = "dp")
  public void f(String id,String isExec,String testCae,String reqType,String reqHost,String reqInterface,String reqData,String expResult,String isDep,String depKey) {
	  
	  try {
		String reqUrl = reqHost +reqInterface ;
		String actResult = null;
		if ("YES".equals(isExec)) {
			if ("GET".equals(reqType)) {
				actResult = HttpReqData.sendGet(reqUrl, reqData);
			}else if ("Post".equals(reqType)) {
				actResult = HttpReqData.senPost(reqUrl, reqData);
			}
		}else {
			System.out.println("此接口不执行");
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
  }

  @DataProvider
  public Object[][] dp() {
	  Object[][] result = null;
	  try {
		  ExcelUtil excelUtil =  new ExcelUtil(filePath);
		  result = excelUtil.getArrayCellValue(0);
	} catch (Exception e) {
		// TODO: handle exception
	}
    return result;
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
