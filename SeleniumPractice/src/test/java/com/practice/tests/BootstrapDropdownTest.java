package com.practice.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.Utils.ExtentTestManager;

public class BootstrapDropdownTest extends Base{

	WebDriver driver;
	
	@BeforeMethod
	public void loadBrowser() {
		driver.get("https://you.yash.com/Pages/default.aspx");
	}

	@Test
	public void dropDownTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Shared Services')]"))));
		driver.findElement(By.xpath("//span[contains(text(),'Shared Services')]")).click();
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='static']//ul[@class='dynamic']//li//a[@class='dynamic menu-item']"));
		for (WebElement item : list) {
			System.out.println(item.getText());
			if (item.getText().equals("Payroll")) {
				item.click();
				System.out.println("Clicked");
				break;
			}
		}
	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
