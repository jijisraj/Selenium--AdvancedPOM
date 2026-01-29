package com.jiji.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
		public static ExtentReports getInstance() {
			

		if(extent==null) {
			File reportDir = new File(System.getProperty("user.dir") + "/reports");
			if (!reportDir.exists()) {
			    reportDir.mkdirs();
			}
			String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportPath =
                    System.getProperty("user.dir")
                    + "/reports/ExtentReport_" + timestamp + ".html";
            System.out.println("Report Path: " + reportPath);
			ExtentSparkReporter spark=new ExtentSparkReporter(reportPath);
			 spark.config().setReportName("Automation Test Report");
	            spark.config().setDocumentTitle("Test Execution Report");
	            spark.config().setTheme(Theme.STANDARD);
			extent=new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Jiji");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", "Selenium TestNG");
		}
		return extent;
	}

}
