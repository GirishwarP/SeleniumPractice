package com.practice.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

public class DropdownsOnYouHomePage extends Base {

	@BeforeMethod
	public void init() {
		WebDriver driver = Base.driver;
		driver.get("https://you.yash.com/Pages/default.aspx");
	}

	@Test
	public void sharedServicesDropdown() {
		String sharedServices = driver.findElement(By.xpath("//span[contains(text(),'Shared Services')]")).getText();
		driver.findElement(By.xpath("//span[contains(text(),'Shared Services')]")).click();
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='static']//ul[@class='dynamic']//li//span[@class='menu-item-text']"));
		
		System.out.println(sharedServices + " : ");
		
		for(WebElement element : list) {
			System.out.println(element.getText());
		}
	}
	
	@Test
	public void businessGroupsDropdown() {
		String businessGroups = driver.findElement(By.xpath("//span[contains(text(),'Business Groups')]")).getText();
		driver.findElement(By.xpath("//span[contains(text(),'Business Groups')]")).click();
		List<WebElement> list = driver.findElements(By.xpath(""));
		
	}
	
	@AfterMethod
	public void cleanUp() {
		driver.quit();
	}
}
