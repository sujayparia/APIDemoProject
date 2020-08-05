package com.api.createTest;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.bodyConfig.UserBody;
import com.api.extentReport.ExtentReport;
import com.api.headerConfig.PostHeader;
import com.api.pathConfig.PostPath;
import com.api.responseValidator.BodyValidator;
import com.api.responseValidator.StatusValidator;
import com.api.utility.DataTable;
import com.api.utility.SerialDeserial;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(ExtentReport.class)
public class CreateUserTest extends TestBase{


	@BeforeMethod
	public void baseURIsetup() throws IOException {
		baseURISetup();
	}

	@Test
	public void createNewUser() throws JsonProcessingException, ParseException {
		Response response = RestAssured.given().headers(PostHeader.defaultHeaders())
				.body(UserBody.createUserBody())
				.when()
				.post(PostPath.CREATE_USER);
		
		System.out.println(response.getBody().asString());
		StatusValidator.statusCodeValidator(response, 201);
		BodyValidator.bodyStringValidator(response, "name", DataTable.getData("Create New User", "name"));
		BodyValidator.bodyStringValidator(response, "job", DataTable.getData("Create New User", "job"));
		DataTable.updateData("Create New User", "id", SerialDeserial.JsonToString(response, "id"));
		
	}


}
