package com.spice.service.creation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.DashBoardDao;
import com.spice.service.creation.response.ApiCount;
import com.spice.service.creation.response.ApiCountResponse;
import com.spice.service.creation.response.ResponseObj;
@Component
@Service
public class DashBoardService {
	
	@Autowired
	private DashBoardDao dashBoardDao;
	
	public ResponseObj apiCount()  throws Exception {
		ApiCountResponse countResponse = dashBoardDao.apiCount();
		List<ApiCount> apiCount =  new ArrayList<ApiCount>();
		apiCount.add(new ApiCount("Total Active Users", countResponse.getTotalActiveUser()));
		apiCount.add(new ApiCount("Total Active Services", countResponse.getTotalActiveService()));
		apiCount.add(new ApiCount("Total API's", countResponse.getTotalApi()));
		return new ResponseObj(apiCount);
	}
	

}
