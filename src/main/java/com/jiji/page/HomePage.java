package com.jiji.page;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jiji.base.BasePage;

public class HomePage extends BasePage {

    private final By welcomeMsg = By.id("flash");
    private final By logoutBtn=By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver) {
    	super(driver);
    }
    public String getWelcomeMessage() {
       return getText(welcomeMsg);
    	
    }
    
    public void logout() {
       click(logoutBtn);
    }
}
