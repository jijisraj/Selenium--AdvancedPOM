package com.jiji.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jiji.driver.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	 protected Logger log = LogManager.getLogger(this.getClass());

	    @BeforeMethod
	    @Parameters("browser")
	    public void setup(@Optional("Chrome")String browser) {

	        WebDriver driver;
	        if (browser == null || browser.isEmpty()) {
	            throw new RuntimeException(
	                "Browser parameter is missing! Run test via TestNG XML with <parameter name=\"browser\" ...>"
	            );
	        }
	            else if (browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	        } else {
	            throw new RuntimeException("Invalid browser: " + browser);
	        }

	        DriverManager.setDriver(driver);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get(com.jiji.utils.PropertyUtils.get("url"));
	    }

	    @AfterMethod
	    public void teardown() {
	        DriverManager.quitDriver();
	    }

}
