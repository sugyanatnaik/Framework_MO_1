package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// @FindBy(xpath = "//span[text()='Log In']") WebElement firstLogin;
	// @FindBy(name = "email") WebElement uname;
	// @FindBy(name = "password") WebElement pass;
	// @FindBy(xpath = "//div[text()='Login']") WebElement finalLogin;

	@FindBy(name = "email")	WebElement uname;
	@FindBy(id = "pass") WebElement pass;
	@FindBy(xpath = "//input[@value='Log In']")	WebElement LoginButton;

	public void loginToCRM(String userName, String password) {
		uname.sendKeys(userName);
		pass.sendKeys(password);
		LoginButton.click();
	}

}
