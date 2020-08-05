package com.api.parameter;

import java.util.HashMap;
import java.util.Map;

import com.api.utility.DataTable;

public class QueryParamater {
	
	public static Map<String, String> getListUsers_QueryParam(String testcase, String column) {
		Map<String,String> queryParam = new HashMap<String,String>();
		queryParam.put("page", DataTable.getData(testcase, column));
		return queryParam;
	}

}
