package com.bookmyroom.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyListener implements ITestListener{
	
	Util util;

	protected static ExtentReports reports;
	protected static ExtentTest test;
	static org.apache.log4j.Logger log = Logger.getLogger(MyListener.class.getName());
	
	public MyListener(WebDriver driver)
	{
	util=new Util(driver);
	}

	 @Override
	 public void onTestStart(ITestResult result) {
		// log.info("on test start");
	  test = reports.startTest(result.getMethod().getMethodName());
	  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	 }
	 @Override
	 public void onTestSuccess(ITestResult result) {
		// log.info("on test success");
	  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	 }

	@Override
	public void onTestFailure(ITestResult result) {		
		try {
			String methodName=result.getName().trim();
			util.takeSnapShot(methodName);
			String file = test.addScreenCapture("C:\\images\\" + result.getMethod().getMethodName() + ".png");
			test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
			test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
		} catch (IOException e) {
			log.error("Error while saving sreeenshot!!",e);
		}
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		 // log.info("on test skipped");
		  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
		 }
	@Override
		 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 }
	@Override
		 public void onStart(ITestContext context) {
		 // log.info("on start");
		  reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
		 }
	@Override
		 public void onFinish(ITestContext context) {
		log.info("Report generated!!");
		  reports.endTest(test);
		  reports.flush();
		 }
	
	

}
