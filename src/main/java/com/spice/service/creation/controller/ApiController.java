package com.spice.service.creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.ApiService;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
	
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping(value = "/apiList", method = RequestMethod.GET)
	public ResponseObj getApiList() throws Exception {
		return apiService.getApiList();
	}
	

}
