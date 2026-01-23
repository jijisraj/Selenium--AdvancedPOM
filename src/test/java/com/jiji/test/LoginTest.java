package com.jiji.test;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jiji.base.BaseTest;
import com.jiji.driver.DriverManager;
import com.jiji.page.HomePage;
import com.jiji.page.LoginPage;
import com.jiji.utils.PropertyUtils;

public class LoginTest extends BaseTest{

    @Test
    public void validLoginTest() {
    	WebDriver driver = DriverManager.getDriver();
    	String username = PropertyUtils.get("username");
        String password = PropertyUtils.get("password");
        //WebDriver driver = DriverManager.getDriver();
        System.out.println("Driver is null? " + (DriverManager.getDriver() == null));
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(username, password);

        Assert.assertTrue(
            homePage.getWelcomeMessage().contains("You logged into a secure area"),
            "Login failed – welcome message not visible"
        );
        homePage.logout();
        Assert.assertTrue(
        		 loginPage.isLoginButtonVisible(),
                 "Logout failed – Login button not visible");
    }
    
}
