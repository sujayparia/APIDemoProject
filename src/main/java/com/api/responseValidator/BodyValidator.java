package com.api.responseValidator;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.api.extentReport.ExtentReport;
import com.api.utility.SerialDeserial;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

@Listeners(ExtentReport.class)
public class BodyValidator extends ExtentReport {
	
	
	public static void bodyStringValidator(Response response, String jsonNode, String expectedValue) throws ParseException {
		
		String value = SerialDeserial.JsonToString(response, jsonNode);
		 
		try {
			Assert.assertEquals(value, expectedValue);
			test.log(Status.PASS, "Expected value-->"+expectedValue+" And actual value-->"+value);
			
		} catch(AssertionError e) {
			test.log(Status.FAIL, "Expected value-->"+expectedValue+" But actual value-->"+value);
		}
		catch (Exception e) {
			test.log(Status.FAIL, e.fillInStackTrace());
		}
		
	}

}
