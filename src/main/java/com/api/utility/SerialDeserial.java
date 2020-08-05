package com.api.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SerialDeserial {
	
	public static String ObjectToJson(Object obj) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(obj);
			
		return body;
		
	}
	

}
