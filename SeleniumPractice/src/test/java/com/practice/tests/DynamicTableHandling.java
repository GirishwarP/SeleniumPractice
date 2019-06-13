package com.practice.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

public class DynamicTableHandling extends Base {

	WebDriver driver;
	List<Double> priceList = new ArrayList<Double>();
	List<String> dataList = new ArrayList<String>();
	Map<String, Double> dataMap = new HashMap<String, Double>();
	List<Map.Entry<String, Double>> mapList = new ArrayList<Map.Entry<String, Double>>();

	@BeforeMethod
	public void init() {
		driver = Base.driver;
		driver.get("http://demo.guru99.com/test/web-table-element.php");
	}
	
	@Test
	public void dynamicTableTest1() {

		System.out.println("----------------------------------Using List of price---------------------------------");
		
		List<WebElement> columns = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[1]"));

		String beforeXpath = "/html[1]/body[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[";
		String afterXpath = "]/td[";

		for (int i = 1; i <= rows.size(); i++) {
			for (int j = 1; j <= columns.size(); j++) {
				WebElement data = driver.findElement(By.xpath(beforeXpath + i + afterXpath + j + "]"));
				WebElement priceData = driver.findElement(By.xpath(beforeXpath + i + afterXpath + 4 + "]"));
				Double price = Double.parseDouble(priceData.getText().toString());
				priceList.add(price);
				break;
			}
		}
		
		double max = priceList.get(0);
		int index=0;
		for(int i=0; i<priceList.size()-1; i++) {			
			if(max < priceList.get(i+1)) {
				max = priceList.get(i+1);
				index = priceList.indexOf(max);
			}
		}
		
		System.out.println("Max Price : " + max);
		
		String data = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + (index+1) + "]")).getText().toString();
		System.out.println("Data For Maximum Price [" + data + "]");
		
		System.out.println("--------------------------------------------------------------------------------------");
	}

	@Test
	public void dynamicTableTest2() {
		
		System.out.println("-----------------Using Map of DataList and Price(Key and Value resp.)-----------------");
		
		List<WebElement> columns = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[1]"));

		String beforeXpath = "/html[1]/body[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[";
		String afterXpath = "]/td[";

		for (int i = 1; i < rows.size(); i++) {
			Double price = 0.0;
			String data = null;
			for (int j = 1; j <= columns.size(); j++) {				
				data = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + i + "]")).getText().toString();
				WebElement priceData = driver.findElement(By.xpath(beforeXpath + i + afterXpath + 4 + "]"));
				price = Double.parseDouble(priceData.getText().toString());
				break;
			}
			dataMap.put(data, price);
		}
		
		for(Map.Entry<String, Double> entry : dataMap.entrySet()) {
			mapList.add(entry);
		}
		
		Collections.sort(mapList, new SortingOnPrice());
		
		System.out.println("Max Price : " + mapList.get(0).getValue());
		System.out.println("Data For Maximum Price [" + mapList.get(0).getKey() + "]");
		
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	/*@AfterMethod
	public void shutDown() { 
		Base.teardown(); 
	} */
}

class SortingOnPrice implements Comparator<Map.Entry<String, Double>>{

	public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
		double price1 = entry1.getValue();
		double price2 = entry2.getValue();
		
		return (int) (price2-price1);
	}
}
