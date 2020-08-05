package com.api.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;


public class SerialDeserial {

	public static JSONParser parser = new JSONParser();

	public static String ObjectToJson(Object obj) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(obj);

		return body;
	}

	public static String JsonToString(Response response, String jsonNode) throws ParseException {

		Object object=parser.parse(response.asString());
		JSONObject jObject = (JSONObject)object;
		
		String value=(String)jObject.get(jsonNode);
		return value;

	}

	public static JSONArray JsonToJsonArray(Response response, String jsonNode) throws ParseException {

		Object object=parser.parse(response.asString());
		JSONObject jObject = (JSONObject)object;
		
		JSONArray value=(JSONArray)jObject.get(jsonNode);
		return value;

	}
	
	public static JSONObject JsonToJsonObject(Response response, String jsonNode) throws ParseException {

		Object object=parser.parse(response.asString());
		JSONObject jObject = (JSONObject)object;
		
		JSONObject value=(JSONObject)jObject.get(jsonNode);
		return value;

	}

}
