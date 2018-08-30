package com.bookmyroom.testng;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.bookmyroom.pages.LoginPage;
import com.bookmyroom.pages.SignUpPage;
import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Util;


@Listeners
public class ApplicationTest {
	

	public static WebDriver driver;
	Util util;
	GetExcelData excelData=new GetExcelData();
	static org.apache.log4j.Logger log = Logger.getLogger(ApplicationTest.class.getName());
	
	//SignUp
	@DataProvider(name = "SignUpSheetData")	  
	 public Object[][] signUpData() throws IOException 
	{		
		return excelData.getData(Constants.signUpExcelPath, "SignUpData");
	}
	
	@Test(dataProvider="SignUpSheetData",groups = {"SignUp"})
	public void signUpTest(String testCaseName,String name,String email,String password,String gender,String age,String phone)
	{
		log.info("------ SignUp - "+testCaseName+" -------");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.signUp(name, email, password, gender, age, phone);
       
	}	
	
	//Login
	@DataProvider(name = "LoginSheetData")	  
	 public Object[][] loginData() throws IOException 
	{		
		return excelData.getData(Constants.signUpExcelPath, "LoginData");
	}
	
	@Test(dataProvider="LoginSheetData",groups = {"Login"})
	public void loginTest(String testCaseName,String email,String password) throws IOException
	{
		log.info("------ Login - "+testCaseName+" -------");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.login(email, password);
	}	
	
	
	@BeforeTest
	public void beforeTest() {	
		String driverPath=Constants.chromeDriverPath;
	    System.setProperty("webdriver.chrome.driver", driverPath);
	    driver = new ChromeDriver();
	    util=new Util(driver);
	}
	
	@AfterTest
	public void afterTest() {	
		driver.close();
	}
	

}
