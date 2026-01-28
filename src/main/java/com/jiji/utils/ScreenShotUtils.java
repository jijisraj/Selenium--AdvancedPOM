package com.jiji.utils;



import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


public class ScreenShotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotDir =
                System.getProperty("user.dir") + "/screenshots";

        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();   // 🔥 IMPORTANT
        }

        String screenshotPath =
                screenshotDir + "/" + testName + "_" + timestamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("📸 Screenshot saved at: " + screenshotPath);

        return screenshotPath;
    }
    
}
