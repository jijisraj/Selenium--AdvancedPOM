package com.jiji.listener;

import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.jiji.base.BaseTest;
import com.jiji.driver.DriverManager;
import com.jiji.utils.ExtentManager;
import com.jiji.utils.ScreenShotUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    //ExtentReports extent = ExtentManager.getReport();
    //ExtentTest test;
	private static final Logger log = LogManager.getLogger(TestListener.class);

	private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
   
    public void onTestStart(ITestResult result) {
    	System.out.println("🔥 LISTENER WORKING for: " + result.getMethod().getMethodName());
    ExtentTest    extenttest = extent.createTest(result.getMethod().getMethodName());
    test.set(extenttest);
        log.info("Test started: " + result.getMethod().getMethodName());
        System.out.println("Test started: " + result.getMethod().getMethodName());
     
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        log.info("Test passed");
        WebDriver driver = DriverManager.getDriver();
            String path = ScreenShotUtils.captureScreenshot(
                    driver,
                    result.getMethod().getMethodName()
            );
            test.get().pass("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
           // test.get().addScreenCaptureFromPath(path);
          //  test.get().pass("Test passed"); 
           // System.out.println("Test passed: " + result.getMethod().getMethodName());

            System.out.println("✅ Screenshot attached to PASS report"); 
        }

    

    public void onTestFailure(ITestResult result) {
        //test.fail(result.getThrowable());
        log.error("Test failed", result.getThrowable());
    	test.get().fail(result.getThrowable());
    	System.out.println("Test failed: " + result.getName());

        Object testClass = result.getInstance();
        WebDriver driver =DriverManager.getDriver();

        String path =
                ScreenShotUtils.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );
        test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        System.out.println("❌ Screenshot attached to FAIL report");
      //  test.get().addScreenCaptureFromPath(path);
       // test.get().fail(result.getThrowable());
       // System.out.println("Test failed: " + result.getMethod().getMethodName());

        
    }
/**
        try {
            Field field = testClass.getClass().getSuperclass()
                    .getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(testClass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            ScreenShotUtils.captureScreenshot(driver, result.getName());
        } 
    **/
   
    public void onFinish(ITestContext context) {
        extent.flush();
        log.info("Extent report flushed");
    }
}
