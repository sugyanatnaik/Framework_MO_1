package com.learnautomation.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utilities.BrowserFactory;
import com.learnautomation.utilities.ConfigDataProvider;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {
		
		Reporter.log("Setting up reprots and Test is getting ready",true);
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/Reports/Facebook_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		Reporter.log("Settings done and Test can be Started",true);
	}
	
	@Parameters({"browser","urlToBeTested"})
	@BeforeClass
	public void setUp(String  browser,String url) {
		Reporter.log("Setting up Browser and getting application ready",true);
		
		//driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());
		driver = BrowserFactory.startApplication(driver, browser, url);
		Reporter.log("Browser and application is up and running",true);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws Exception {
		
		Reporter.log("Test is about to End",true);
		
		if (result.getStatus() == ITestResult.FAILURE) {	
			logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
		Reporter.log("Test completed >>> Reports Generated",true);

	}


	@AfterClass
	public void quit() {
		BrowserFactory.quitBrowser(driver);
	}
}
