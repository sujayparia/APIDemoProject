package com.api.extentReport;

import java.util.Date;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestBase implements ITestListener{

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public String folder = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());

	public void reportSetUp() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop = new Properties();
		prop.load(fis);

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder+"\\"+"ExtentReport.html");
		htmlReporter.config().setReportName(prop.getProperty("REPORTNAME"));
		htmlReporter.config().setDocumentTitle(prop.getProperty("REPORTTITLE"));
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", prop.getProperty("OS"));
		extent.setSystemInfo("AUTHOR", prop.getProperty("AUTHOR"));

	}

	public void reportEnd() {
		extent.flush();
	}

	public void sendEmailReport() throws EmailException {

		String date = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("gymvshape@gmail.com", "sujay16041990@"));
		email.setSSLOnConnect(true);
		email.addTo("sujayparia@gmail.com", "sujay paria");
		email.addTo("gymvshape@gmail.com", "vshape gym");
		email.addTo("sohinitune@gmail.com", "sohini");
		email.setFrom("gymvshape@gmail.com", "Me");
		email.setSubject("Automation Test Report :: DateTime "+date);
		email.setMsg("Hi All, Please find attached the Automation Test Report");


		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder+"\\"+"ExtentReport.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("API Automation Report");
		attachment.setName("Extent Report");

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}



	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test Case Passed");

	}

	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test Case Failed");

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Case Skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		try {
			reportSetUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		reportEnd();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		  try { 
			  sendEmailReport(); 
		} catch (EmailException e) {
			e.printStackTrace(); 
		}
		 

	}

}
