package com.spice.service.creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.DashBoardService;

@RestController
@RequestMapping(value = "/dashBoard")
public class DashBoardController {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@RequestMapping(value = "/Details", method = RequestMethod.GET)
	public ResponseObj apiCount() throws Exception {
		return dashBoardService.apiCount();
	}
	

}
