package com.practice.tests;

import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.Base;
import com.Utils.ExtentTestManager;

public class DataProviderTest extends Base{

	WebDriver driver;
	private ExcelFileReader excelReader = new ExcelFileReader();
	
	@BeforeMethod
	public void loadBrowser() {
		driver = Base.driver;
		driver.get("https://www.google.com/");
	}
	
	@Test(dataProvider = "credentials")
	public void dataProviderTest(ExcelData excelData) {
		String username = excelData.getUserName();
		String passwrod = excelData.getPassword();
		
		Assert.assertEquals(username.equals(passwrod), false);
	}
	
	@DataProvider(name="credentials")
	public Iterator<ExcelData> testData() throws Exception{
		return excelReader.readExcel("D:/MyWork/SeleniumPractice/TestData/TestData.xlsx", "TestData.xlsx", "Sheet1").iterator();
	}
	
	@AfterMethod
	public void shutdown(ITestResult result) throws InterruptedException, IOException {
		
		String status = null;
		if(ITestResult.FAILURE==result.getStatus())
			status = "Failed";
		else if(ITestResult.SUCCESS==result.getStatus())
			status = "Passed";
		
		excelReader.writeExcel("D:/MyWork/SeleniumPractice/TestData/TestData.xlsx", "TestData.xlsx", "Sheet1", status,((ExcelData)result.getParameters()[0]).getRowId());
		Thread.sleep(2000);
		driver.quit();
	}
}
