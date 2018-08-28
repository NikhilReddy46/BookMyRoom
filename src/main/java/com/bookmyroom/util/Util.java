package com.bookmyroom.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Util {
	
	static org.apache.log4j.Logger log = Logger.getLogger(Util.class.getName());
	static WebDriver driver;
	
	public Util(WebDriver driver)
	{
		Util.driver=driver;
	}
	
	public void maximize()
	{
		driver.manage().window().maximize();
	}
	
	//Open URL
	public void navigateTo(String url)
	{
		driver.get(url);
	}
	
	//Select by CSS 
	public WebElement selectByCSS(String path)
	{
		return driver.findElement(By.cssSelector(path));
	}
	
	//Select by X-path
	public WebElement selectByXpath(String path)
	{
		return driver.findElement(By.xpath(path));
	}
	
	
	//Select using CSS and Click
		public void selectAndClick(String path)
		{
			selectByCSS(path).click();
		}
		
		//Select using X-path and Click
		public void selectAndClickX(String path)
		{
			selectByXpath(path).click();
		}
		
		
		//Select and Type into text field
		public void selectAndTypeInto(String path,String text)
		{
			selectByCSS(path).sendKeys(text);
		}
		
		public void selectAndTypeIntoById(String id,String text)
		{
			driver.findElement(By.id(id)).sendKeys(text);
		}
		
		public void selectAndTypeIntoByName(String name,String text)
		{
			driver.findElement(By.name(name)).sendKeys(text);
		}
		
		//Select and Type into text field
		public void selectAndTypeIntoX(String path,String text)
		{
			selectByXpath(path).sendKeys(text);
		}
		
		//Select and get text
		public String selectAndGetText(String path)
		{
			return driver.findElement(By.cssSelector(path)).getText();
		}
		
		//Select and Clear text field
		public void selectAndClear(String path)
		{
			driver.findElement(By.cssSelector(path)).clear();
		}
		

		public String getTitle()
		{
			return driver.getTitle();
		}
		
		public void verify(String path,String message)
		{
			Assert.assertEquals(selectAndGetText(path), message);
			
		}
		
		//Take Screenshot
		public void takeSnapShot(String name) throws IOException{

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)driver);

	        //Call getScreenshotAs method to create image file

	                File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

	                File destFile=new File("D:\\OxygenWorkspace\\bookmyroom\\output\\"+name+".png");

	                //Copy file at destination

	                FileUtils.copyFile(srcFile, destFile);
	                
	                System.out.println("Done!");

	    }
		
		public void doAction(String locatorType,String locatorValue,String parameter)
		{
			if("CSS".equalsIgnoreCase(locatorType))
    			selectAndTypeInto(locatorValue,parameter);
    		else if("Xpath".equalsIgnoreCase(locatorType))
    			selectAndTypeIntoX(locatorValue,parameter);
    		else if("Id".equalsIgnoreCase(locatorType))
    			selectAndTypeIntoById(locatorValue,parameter);
    		else if("name".equalsIgnoreCase(locatorType))
    			selectAndTypeIntoByName(locatorValue,parameter);
    		else
    			log.debug("Invalid locator type!");
		}


}
