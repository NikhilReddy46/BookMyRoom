package com.bookmyroom.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObjectsRepository {
	
	private final static String nameObject=""; 
	public static String ageObject=""; 
	public static String emailObject=""; 
	public static String pwdObject=""; 
	public static String phoneObject=""; 
	public static String genderObjectMale="";
	public static String genderObjectFemale="";
	

	@FindBy (id=nameObject)
	public static WebElement userName;
	
}
