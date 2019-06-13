package com.practice.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.Utils.ExtentTestManager;

public class ScreenShotTest extends Base{

	WebDriver driver;
	
	@BeforeMethod
	public void loadBrowser() {
		initialization();
		driver = Base.driver;
		driver.get("https://www.google.com/");
	}
	
	@Test
	public void screenShotTest() {
		WebElement logo = driver.findElement(By.xpath("//img[@alt='Google']"));
		Assert.assertEquals(logo.isDisplayed(), false); //failed deliberately 
	}
	
	@AfterMethod
	public void shutdown(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()) {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("D:/MyWork/SeleniumPractice/screenShots/"+result.getName()+".png"));
			System.out.println("Screenshot captured");
		}
		Thread.sleep(2000);
		driver.quit();
	}
}
