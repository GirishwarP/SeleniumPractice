package com.practice.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.Utils.ExtentTestManager;

public class JavaScriptTest extends Base{

	WebDriver driver;
	public static JavascriptExecutor  js;
	
	@BeforeMethod
	public void loadBrowser() {
		initialization();
		driver = Base.driver;
		driver.get("https://www.google.com/");
		js = (JavascriptExecutor)driver;
	}
	
	//JavascriptExecutor demo
	@Test
	public void javaScriptTest() throws InterruptedException {
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Testing");
		WebElement button = driver.findElement(By.xpath("//div[@class='FPdoLc VlcLAe']//input[@value='Google Search']"));
		
		//Highlighting element
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", button);
		
		//paused script for 5 seconds to check highlighting of search button
		Thread.sleep(5000);
		
		//click on search button
		js.executeScript("arguments[0].click();", button);
	
		//Fetching the domain name of the site
		String domain = js.executeScript("return document.domain;").toString();
		System.out.println("Domain name -> " + domain );
		
		//fetching the URL of site
		String url = js.executeScript("return document.URL;").toString();
		System.out.println("URL -> " + url);
		
		//fetching the title of the page
		String title = js.executeScript("return document.title;").toString();
		System.out.println("Title -> " + title);
		
		//Navigating to new url
		js.executeScript("window.location = 'https://paytm.com/'");
		
		//Vertical scroll down by 600 pixels 
		js.executeScript("window.scrollBy(0,2000)");
		
		//Refresh browser
		js.executeScript("history.go(0)");
	}
	
	@AfterMethod
	public void shutdown() throws InterruptedException {
		//To generate Alert window using JavascriptExecutor. Display the alert message 	
		js.executeScript("alert('Shutting down the browser');");
		Thread.sleep(5000);
		driver.quit();
	}
}
