package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;
	
	// This is new commit from Sugyan
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}


	@FindBy(name = "email")	WebElement uname;
	@FindBy(id = "pass") WebElement pass;
	@FindBy(xpath = "//input[@value='Log In']")	WebElement LoginButton;

	public void loginToCRM(String userName, String password) {
		uname.sendKeys(userName);
		pass.sendKeys(password);
		LoginButton.click();
	}

}
