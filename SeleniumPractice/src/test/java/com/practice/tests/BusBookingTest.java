package com.practice.tests;

import java.util.Calendar;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

public class BusBookingTest extends Base{
	
	WebDriver driver;
	WebElement busIcon;
	WebElement from;
	WebElement to;
	WebElement calendarBtn1;
	WebElement calendarBtn2;
	WebElement nextLink; 
	WebElement date;
	WebElement searchBtn;
	Scanner sc;
	String departureDate;
	String returnDate;
	JavascriptExecutor j;

	@BeforeMethod
	public void data() throws InterruptedException {
		Base.initialization();
		driver = Base.driver;
		sc = new Scanner(System.in);
		
		System.out.println("Departure Date(DD MON YYYY)");
		departureDate = sc.nextLine(); 
		
		System.out.println("Return Date(DD MON YYYY)");
		returnDate = sc.nextLine();	
		
		driver.get("https://paytm.com/");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[@title='Bus']")).click();
		
		from = driver.findElement(By.xpath("//div[@class='row bottom-xs _1CqX']//div[2]//div[1]//span[1]//div[1]//input[1]"));
		from.sendKeys("Pune");
		
		to = driver.findElement(By.xpath("//div[@class='row bottom-xs _1CqX']//div[4]//div[1]//span[1]//div[1]//input[1]"));
		to.sendKeys("Bengaluru");
		
		calendarBtn1 = driver.findElement(By.xpath("//label[text()='Departure Date']/preceding::img[1]"));
		calendarBtn1.click();	
		
		Thread.sleep(5000);
		
		j =(JavascriptExecutor) driver;
		j.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--next']")));
		
		searchBtn = driver.findElement(By.xpath("//button[@class='button button--default button--bold _3Su5']"));
		calendarBtn2 = driver.findElement(By.xpath("//label[text()='Departure Date']/following::img[1]"));
	}
	
	@Test
	public void booking() {
		String[] depDateArray = departureDate.split(" ");
		String depDate = depDateArray[0];
		String depYr = depDateArray[2];
		
		String[] returnDateArray = returnDate.split(" ");
		String returndate = returnDateArray[0];
		String returnYr = returnDateArray[2];
		
		
		int yearDiff1 = Integer.parseInt(depYr) - Calendar.getInstance().get(Calendar.YEAR);
		if(yearDiff1 != 0) {
			if(yearDiff1 > 0) {
				for(int i=0; i<yearDiff1; i++) {
					j =(JavascriptExecutor) driver;
					j.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--next']")));
				}
			}
		}
		
		date = driver.findElement(By.xpath("//div[@class='react-datepicker__day'][contains(text(),'"+ depDate +"')]"));
		date.click();
		
		calendarBtn2.click();
		int yearDiff2 = Integer.parseInt(returnYr) - Calendar.getInstance().get(Calendar.YEAR);
		if(yearDiff2 != 0) {
			if(yearDiff1 > 0) {
				for(int i=0; i<yearDiff1; i++) {
					j =(JavascriptExecutor) driver;
					j.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--next']")));
				}
			}
		}
		
		date = driver.findElement(By.xpath("//div[@class='react-datepicker__day'][contains(text(),'"+ returndate +"')]"));
		date.click();
		
		searchBtn.click();
	}
}
