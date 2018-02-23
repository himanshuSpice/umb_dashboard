package com.spice.service.creation.request;

import lombok.Data;

@Data
public class DbXmlConfiguration {
	private String jdbcUrl;
	private String  jdbcUserName;
	private String  jdbcPassword;

}
