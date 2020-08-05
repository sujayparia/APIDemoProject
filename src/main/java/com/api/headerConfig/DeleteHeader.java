package com.api.headerConfig;

import java.util.HashMap;
import java.util.Map;

public class DeleteHeader {
	
	public static Map<String, String> defaultHeaders() {
		Map<String,String> defaultHeader = new HashMap<String,String>();
		defaultHeader.put("content-type", "application/json");
		return defaultHeader;
	}

}
