package com.bookmyroom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.bookmyroom.util.Constants;
import com.bookmyroom.util.GetExcelData;
import com.bookmyroom.util.Util;

public class SignUpPage {
	
	static WebDriver driver;
	GetExcelData excelData;
	
	Util util=new Util(driver);	
	static org.apache.log4j.Logger log = Logger.getLogger(SignUpPage.class.getName());
	
	public SignUpPage(WebDriver driver){
		SignUpPage.driver=driver;
	}
	
	public void signUp(String name,String email,String password,String gender,String age,String phone) 
	{
		
		String data[][];
		
			data = excelData.getData(Constants.signUpExcelPath, "SignUp");
		
		
		for(int i=0;i<data.length;i++)
        {
			
			switch(data[i][Constants.keyword_col])
	    	{
	    	
	    	case "navigateTo":
	    		util.navigateTo(data[i][Constants.parameter_col]);
	    		break;
	    		
	    	case "enterName":
	    		
	    		util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col], name);
	    		break;
	    		
	    	case "enterEmail":
	    		util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col], email);
	    		break;
	    		
	    	case "enterPassword":
	    		util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col], password);
	    		break;
	    		
	    	case "selectGender":        		
	    		if(gender.equalsIgnoreCase("Male"))
	    			util.selectAndClick(Constants.genderMaleCSS);
	    		else if(gender.equalsIgnoreCase("Female"))
	    			util.selectAndClick(Constants.genderFemaleCSS);
	    		else
	    			log.debug("Invalid gender mentioned!");
	    		//util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col],gender);
	    		break;
	    		
	    	case "enterAge":
	    		util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col], age);
	    		break;
	    		
	    	case "enterPhone":
	    		util.doAction(data[i][Constants.pathType_col], data[i][Constants.path_col], phone);
	    		break;
	    	
	    	case "clickSubmit":
	    		util.selectAndClick(data[i][Constants.path_col]);
	    		break;
	    		
	    	case "verify":
	    		util.verify(data[i][Constants.path_col], data[i][Constants.parameter_col]);  
	    		break;
	    		
	    	default:log.debug("Invalid Keyword");
	    	
	    	}
        }
		
	}

}
