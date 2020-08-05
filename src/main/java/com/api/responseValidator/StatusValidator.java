package com.api.responseValidator;

import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.api.extentReport.ExtentReport;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

@Listeners(ExtentReport.class)
public class StatusValidator extends ExtentReport{
	
	public static void statusCodeValidator(Response response, int ExpectedStatusCode) {
		 
		try {
			Assert.assertEquals(response.getStatusCode(), ExpectedStatusCode);
			test.log(Status.PASS, "Expected status code-->"+ExpectedStatusCode+" And actual status code-->"+response.getStatusCode());
			
		} catch(AssertionError e) {
			test.log(Status.FAIL, "Expected status code-->"+ExpectedStatusCode+" But actual status code-->"+response.getStatusCode());
		}
		catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		
	}
	
	
	

}
