package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.DeploymentDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.request.AddMsisdnRequest;
import com.spice.service.creation.request.CancelDeploymentRequest;
import com.spice.service.creation.request.DeployUatRequest;
import com.spice.service.creation.request.DeployViewLogsRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.ViewLogsResponse;
import com.spice.service.creation.response.ViewMsisdnResponse;


@Component
@Service
public class DeploymentService {
	
	@Autowired
	private DeploymentDao deploymentDao;
	
	public ResponseObj addMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.addMsisdn(addMsisdnRequest,  userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj updateMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.updateMsisdn(addMsisdnRequest,  userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj deleteMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.deleteMsisdn(addMsisdnRequest,  userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj viewMsisdn(String  userId)  throws Exception {
		List<ViewMsisdnResponse> response = deploymentDao.viewMsisdn(userId);
		if(response.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
		return new ResponseObj(response);
	}
	
	public ResponseObj deployViewLogs(DeployViewLogsRequest deployViewLogsRequest, String userId)  throws Exception {
		List<ViewLogsResponse> response = deploymentDao.deployViewLogs(deployViewLogsRequest, userId);
		if(response.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
		return new ResponseObj(response);
	}
	public ResponseObj deployUat(DeployUatRequest deployUatRequest,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.deployUat(deployUatRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj deployLive(DeployUatRequest deployUatRequest,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.deployLive(deployUatRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	public ResponseObj cancelDeployment(CancelDeploymentRequest request ,String  userId)  throws Exception {
		CallableStatement verifyResponses = deploymentDao.cancelDeployment(request, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	
}
