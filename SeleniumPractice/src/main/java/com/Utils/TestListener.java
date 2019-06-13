package com.Utils;

import com.base.Base;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends Base implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");
		// Start operation for extentreports.
		ExtentTestManager.startTest(result.getMethod().getMethodName(), "");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(result) + " succeed");
		// Extentreports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(result) + " failed");

		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = result.getInstance();
		WebDriver webDriver = Base.driver;

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		// Extentreports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(result) + " skipped");
		// Extentreports log operation for skipped tests.
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));

	}

	public void onStart(ITestContext context) {
		System.out.println("I am in onStart method " + context.getName());
		context.setAttribute("WebDriver", this.driver);

	}

	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish method " + context.getName());
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}
}