package com.practice.tests;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.Base;

public class WindowHandling extends Base {

	WebDriver driver;
	
	@BeforeMethod
	public void init() {
		initialization();
		driver = Base.driver;
		driver.get("http://demo.guru99.com/popup.php");
	}
	
	@Test
	public void windowHnadlingTest() {
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();		
		
		//To handle new opened window
		Set<String> childWindowIds = driver.getWindowHandles();
		Iterator<String> iterator = childWindowIds.iterator();
		
		while(iterator.hasNext()) {
			String childWindow = iterator.next();
			
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				//Switching to child window
				driver.switchTo().window(childWindow);
				driver.findElement(By.name("emailid")).sendKeys("patilgirish1990@gmail.com");
				driver.findElement(By.name("btnLogin")).click();
				
				//closing child window
				driver.close();
			}
		}
		
		//Switching to parent window
		driver.switchTo().window(mainWindow);
	}
}
