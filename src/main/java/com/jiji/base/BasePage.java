package com.jiji.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jiji.driver.DriverManager;

public class BasePage {
	 protected WebDriver driver;
	    protected WebDriverWait wait;

	    public BasePage(WebDriver driver) {
	        if (driver == null) throw new IllegalArgumentException("Driver is null!");
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	    protected WebElement waitForElement(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    protected void click(By locator) {
	        waitForElement(locator).click();
	    }

	    protected void type(By locator, String value) {
	        WebElement element = waitForElement(locator);
	        element.clear();
	        element.sendKeys(value);
	    }

	    protected String getText(By locator) {
	        return waitForElement(locator).getText();
	    }
}
