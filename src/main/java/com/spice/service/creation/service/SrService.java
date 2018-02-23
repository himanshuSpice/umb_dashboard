package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.SrDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.request.CommentViewRequest;
import com.spice.service.creation.request.CreateCommentRequest;
import com.spice.service.creation.request.EditCommentRequest;
import com.spice.service.creation.request.SrCreateServicRequest;
import com.spice.service.creation.request.SrListRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.SrListResponse;
import com.spice.service.creation.response.ViewCommentResponse;


@Component
@Service
public class SrService {
	
	@Autowired
	private SrDao srDaos;
	
	public ResponseObj createService(SrCreateServicRequest srCreateServicRequest , String userId )throws Exception {
		CallableStatement verifyResponses = srDaos.createService(srCreateServicRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	public ResponseObj srList(SrListRequest srListRequest, String userId )throws Exception {
		List<SrListResponse> viewUserResponses = srDaos.srList(srListRequest, userId);
		if(viewUserResponses.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
			
		return new ResponseObj(viewUserResponses);
	}
	
	public ResponseObj editComment(EditCommentRequest editCommentRequest, String userId )throws Exception {
		CallableStatement verifyResponses = srDaos.editComment(editCommentRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj createComment(CreateCommentRequest createCommentRequest, String userId )throws Exception {
		CallableStatement verifyResponses = srDaos.createComment(createCommentRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj commentView(CommentViewRequest commentViewRequest, String userId )throws Exception {
		List<ViewCommentResponse> viewUserResponses = srDaos.commentView(commentViewRequest, userId);
		if(viewUserResponses.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
			
		return new ResponseObj(viewUserResponses);
	}
}
