package com.jiji.base;

import java.lang.reflect.Method;
import java.time.Duration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jiji.driver.DriverManager;
import com.jiji.utils.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	 protected Logger log = LogManager.getLogger(this.getClass());
	 private static ExtentReports extent;
	protected  WebDriver driver;
	public WebDriver getDriver() {
		return driver;
	}
	@BeforeSuite
	public void setupReport() {
	    System.out.println("Initializing Extent Report...");
	    extent = ExtentManager.getInstance();
	}

	    @BeforeMethod
	    @Parameters("browser")
	    public void setup(Method method,@Optional("Chrome")String browser) {
	    	 log.info("========= TEST START: " + method.getName() + " =========");
	    	 log.info("Launching browser: " + browser);
	       // WebDriver driver;
	        if (browser == null || browser.isEmpty()) {
	            throw new RuntimeException(
	                "Browser parameter is missing! Run test via TestNG XML with <parameter name=\"browser\" ...>"
	            );
	        }
	        
	            else if (browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--remote-allow-origins=*");
	            driver = new ChromeDriver(options);
	           // driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	        } else {
	            throw new RuntimeException("Invalid browser: " + browser);
	        }

	        DriverManager.setDriver(driver);
	        log.info("Maximizing browser window");
	        driver.manage().window().maximize();
	        log.info("Setting implicit wait to 10 seconds");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        log.info("Opening application URL: " +" url");
	       
	        driver.get(com.jiji.utils.PropertyUtils.get("url"));
	        log.info("Browser setup completed successfully");
	    }

	    @AfterMethod
	    public void teardown(Method method) {
	    	log.info("Closing browser for test: " + method.getName());

	       DriverManager.quitDriver();
	       log.info("========= TEST END: " + method.getName() + " =========\n");
	    }
	    @AfterSuite
	    public void tearDownReport() {
	        if (extent != null) {
	            extent.flush();
	            System.out.println("Extent Report Flushed");
	        }
	    }

	   
}
