package com.jiji.page;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jiji.base.BasePage;
import com.jiji.driver.DriverManager;
import com.jiji.utils.WaitUtils;

public class LoginPage extends BasePage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
   
    public LoginPage(WebDriver driver) {
    	super(driver);
    }

    public HomePage login(String user, String pass) {
        //driver.findElement(username).sendKeys(user);
    	WaitUtils.waitForElementVisible(driver, driver.findElement(username)).sendKeys(user);

        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }

    public boolean isLoginButtonVisible() {
        return driver.findElement(By.cssSelector("button[type='submit']")).isDisplayed();
    }
}
