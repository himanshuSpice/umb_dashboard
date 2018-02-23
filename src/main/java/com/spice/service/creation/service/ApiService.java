package com.spice.service.creation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.ApiDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.response.ApiListResponse;
import com.spice.service.creation.response.ResponseObj;

@Component
@Service
public class ApiService {
	
	@Autowired
	private ApiDao apiDao;
	
	public ResponseObj getApiList() throws Exception {
		List<ApiListResponse> apiListResponses =   apiDao.getApiList();
		if(apiListResponses.isEmpty()) 
			throw new GenericException("Success", "No Record found", 125);
			
		return new ResponseObj(apiListResponses);
	}
	

}
