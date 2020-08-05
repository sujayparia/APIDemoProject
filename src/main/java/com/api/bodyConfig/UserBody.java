package com.api.bodyConfig;

import com.api.pojo.User;
import com.api.utility.DataTable;
import com.api.utility.SerialDeserial;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UserBody {
	
	public static String createUserBody() throws JsonProcessingException {
		User user = new User();
		user.setJob(DataTable.getData("Create User", "job"));
		user.setName(DataTable.getData("Create User", "name"));
		
		String body = SerialDeserial.ObjectToJson(user);
		return body;
		
	}

	public static String updateUserBody() throws JsonProcessingException {
		User user = new User();
		user.setJob(DataTable.getData("Update User", "job"));
		user.setName(DataTable.getData("Update User", "name"));
		
		String body = SerialDeserial.ObjectToJson(user);
		return body;
		
	}
}
