package com.spice.service.creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.request.AddMsisdnRequest;
import com.spice.service.creation.request.DeployUatRequest;
import com.spice.service.creation.request.DeployViewLogsRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.DeploymentService;

@RestController
@RequestMapping(value = "/deployment")
public class DeploymentController {
	
	@Autowired
	private DeploymentService deploymentService;
	
	@RequestMapping(value = "/addMsisdn", method = RequestMethod.POST)
	public ResponseObj addMsisdn(@RequestBody AddMsisdnRequest addMsisdnRequest, @RequestHeader("userId") String userId) throws Exception {
		return deploymentService.addMsisdn(addMsisdnRequest,  userId);
	}
	
	@RequestMapping(value = "/updateMsisdn", method = RequestMethod.POST)
	public ResponseObj updateMsisdn(@RequestBody AddMsisdnRequest addMsisdnRequest, @RequestHeader("userId") String userId) throws Exception {
		return deploymentService.updateMsisdn(addMsisdnRequest,  userId);
	}
	
	@RequestMapping(value = "/deleteMsisdn", method = RequestMethod.POST)
	public ResponseObj deleteMsisdn(@RequestBody AddMsisdnRequest addMsisdnRequest, @RequestHeader("userId") String userId) throws Exception {
		return deploymentService.deleteMsisdn(addMsisdnRequest, userId);
	}
	
	@RequestMapping(value = "/viewMsisdn", method = RequestMethod.GET)
	public ResponseObj viewMsisdn(@RequestHeader("userId") String userId) throws Exception {
		return deploymentService.viewMsisdn(userId);
	}
	
	@RequestMapping(value = "/deployViewLogs", method = RequestMethod.POST)
	public ResponseObj deployViewLogs(@RequestBody DeployViewLogsRequest deployViewLogsRequest,@RequestHeader("userId") String userId) throws Exception {
		return deploymentService.deployViewLogs(deployViewLogsRequest, userId);
	}
	
	@RequestMapping(value = "/deployUat", method = RequestMethod.POST)
	public ResponseObj deployUat(@RequestBody DeployUatRequest deployUatRequest, @RequestHeader("userId") String userId) throws Exception {
		return deploymentService.deployUat(deployUatRequest, userId);
	}
	
	@RequestMapping(value = "/deployLive", method = RequestMethod.POST)
	public ResponseObj deployLive(@RequestBody DeployUatRequest deployUatRequest, @RequestHeader("userId") String userId) throws Exception {
		return deploymentService.deployLive(deployUatRequest, userId);
	}
	

}
