package com.api.getTest;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.extentReport.ExtentReport;
import com.api.headerConfig.GetHeader;
import com.api.parameter.QueryParamater;
import com.api.pathConfig.GetPath;
import com.api.responseValidator.StatusValidator;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(ExtentReport.class)
public class GetUserTest extends TestBase {

	@BeforeMethod
	public void baseURIsetup() throws IOException {
		baseURISetup();
	}

	@Test
	public void getListUsers() {

		Response response = RestAssured.given().headers(GetHeader.defaultHeaders())
				.queryParams(QueryParamater.getListUsers_QueryParam("List Users", "page"))
				.when()
				.get(GetPath.LIST_USERS);

		StatusValidator.statusCodeValidator(response, 200);
	}


	@Test
	public void getListUsers2() {

		Response response = RestAssured.given().headers(GetHeader.defaultHeaders())
				.queryParams(QueryParamater.getListUsers_QueryParam("List Users", "page"))
				.when()
				.get(GetPath.LIST_USERS);

		StatusValidator.statusCodeValidator(response, 400);
	}


}
