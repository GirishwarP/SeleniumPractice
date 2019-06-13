package com.practice.tests;

import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

public class LinksTesting extends Base {

	WebDriver driver;
	
	@BeforeMethod
	public void init() {
		initialization();
		driver = Base.driver;
		driver.get("https://www.amazon.com/");
	}
	
	@Test
	public void linksAccessibilityTest() {
		List<WebElement> linksList = driver.findElements(By.xpath("//a"));
		
		System.out.println("Total Number of Links : " + linksList.size());
		
		Iterator<WebElement> iterator = linksList.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().isEnabled()) {
				System.out.print(iterator.next().getText() + " is Working\n");
			}else
				System.out.print(iterator.next().getText() + " is not working\n");
		}
	}
}
