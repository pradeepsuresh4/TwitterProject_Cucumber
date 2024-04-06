package com.utilities;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import x.stepdefinition.StepDefinition;

public class CommonMethods {
 static WebDriver driver = StepDefinition.driver;
	public static void implicitWait(int time) {
		// implicit wait
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	public static void explicitWait(WebElement element, int time) {
		// wait for an element
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	
	public static void screenSnap(String name){
		// to take screenshot
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File ("C:\\Users\\prade\\eclipse-GitHub Projects\\TwitterProject\\Screenshots\\" + name + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
