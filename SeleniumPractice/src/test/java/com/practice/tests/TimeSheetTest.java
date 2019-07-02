package com.practice.tests;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

public class TimeSheetTest extends Base {

	static JavascriptExecutor js;
	static Scanner sc = new Scanner(System.in);
	static String rangeXpath = "//h1[contains(@role, 'presentation')]";

	@BeforeMethod
	public void loadBrowser() {
		initialization();
		driver.get("https://you.yash.com/Pages/default.aspx");
	}

	@Test
	public void timeSheetTest() throws InterruptedException {
		boolean isRegularized = true;
		openTimeSheet();

		System.out.println("Enter future Date(dd-mm-yyyy) to regularize : ");
		String given = sc.next();
		int givenDate = getGivenDate(given.trim());
		int givenMonth = getGivenMonth(given.trim());
		String monthToReg = new DateFormatSymbols().getMonths()[givenMonth - 1];
		int currentDate = getCurrentDate();
		int currentMonth = getCurrentMonth();

		while (isRegularized) {
			int min = minDate();
			int max = maxDate();

			if (givenMonth >= currentMonth) {
				if (givenDate > currentDate) {
					if (givenDate > max) {
						driver.findElement(By.xpath(
								"//button//span[contains(@class, 'tsIconSlimArrowRight globalIconFont1Support tsWeekChangerIcon')]"))
								.click();
						System.out.println("Clicked on next button");
						Thread.sleep(10000);
					} else if (currentDate < givenDate) {
						regularizeToDate(givenDate, monthToReg);
						//String errorMsg = driver.findElement(By.xpath("//div[contains(@class, 'tsDialogPopupContent')]")).getText();
						String errorMsg = driver.findElement(By.id("5399__dialogBox")).getText();
						System.out.println(errorMsg);
						isRegularized = false;
					}
				}
			}
		}
	}

	public int minDate() {
		List<WebElement> list = driver.findElements(By.xpath(rangeXpath));
		return Integer.parseInt(list.get(0).getText());
	}

	public int maxDate() {
		List<WebElement> list = driver.findElements(By.xpath(rangeXpath));
		return Integer.parseInt(list.get(list.size() - 1).getText());
	}

	public int getCurrentDate() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	public int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public int getGivenDate(String date) {
		String[] arr = date.split("-");
		return Integer.parseInt(arr[0]);
	}

	public int getGivenMonth(String date) {
		String[] arr = date.split("-");
		return Integer.parseInt(arr[1]);
	}

	public void regularizeToDate(int date, String month) {
		String beforeXpath = "//button[contains(@aria-label, ' ";
		String afterXpath = " 9 Hours 0 Minutes Take over planned working time.')]";
		String xpath = beforeXpath + month + " " + date + afterXpath;

		driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.xpath("//button[contains(text(),'submit')]")).click();
	}
	
	public void alertHandling() throws InterruptedException {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Error Message : " + alertText);
		Thread.sleep(5000);
		alert.accept();
	}

	public void openTimeSheet() {
		// clicking on infogram button
		driver.findElement(By.xpath("//a[contains(@title, 'YASH Infogram')]")).click();

		// switching to infogram page
		String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();

		while (it.hasNext()) {
			String childWindow = it.next();
			if (!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}

		// clicking on time sheet button
		driver.findElement(By.id("__tile3")).click();
	}
}

// Getting week range
/*
 * js = (JavascriptExecutor) driver; String range1 = (String) js.
 * executeScript("return document.getElementById('7__weekInfo').children[0].offsetParent.innerText"
 * ); System.out.println(range1);
 * 
 * String range2 =
 * driver.findElement(By.xpath("//div//span[contains(@class, 'tsDaysRange')]")).
 * getText(); System.out.println(range2);
 */
