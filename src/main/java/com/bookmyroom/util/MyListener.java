package com.bookmyroom.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;

import com.bookmyroom.testng.ApplicationTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyListener extends ApplicationTest implements ITestListener {
	 protected static WebDriver driver;
	 protected static ExtentReports reports;
	 protected static ExtentTest test;
	 static org.apache.log4j.Logger log = Logger.getLogger(MyListener.class.getName());
	 
	// @Override
	 public void onTestStart(ITestResult result) {
	  log.debug("on test start");
	  test = reports.startTest(result.getMethod().getMethodName());
	  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	 }
	 
	// @Override
	 public void onTestSuccess(ITestResult result) {
		 log.debug("on test success");
	  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	 }
	 
	// @Override
	 public void onTestFailure(ITestResult result) {
		 log.debug("on test failure");
	  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
	  TakesScreenshot ts = (TakesScreenshot) driver;
	  File src = ts.getScreenshotAs(OutputType.FILE);
	  try {
	   FileUtils.copyFile(src, new File("C:\\Users\\M1047105\\Desktop\\images\\" + result.getMethod().getMethodName() + ".png"));
	   String file = test.addScreenCapture("C:\\Users\\M1047105\\Desktop\\images\\" + result.getMethod().getMethodName() + ".png");
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
	  } catch (IOException e) {
		  log.debug(e);
	  }
	 }
	 
	// @Override
	 public void onTestSkipped(ITestResult result) {
		 log.debug("on test skipped");
	  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	 }
	 
	// @Override
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 log.debug("on test sucess within percentage");
	 }
	 
	// @Override
	 public void onStart(ITestContext context) {
		 log.debug("on start");
	 driver = ApplicationTest.driver; // Set the drivers path in environment variables to avoid code(System.setProperty())
	  reports = new ExtentReports("C:\\Users\\M1047105\\Desktop\\images\\"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
	 }
	 
	// @Override
	 public void onFinish(ITestContext context) {
		 log.debug("on finish");
	  reports.endTest(test);
	  reports.flush();
	 }
	}