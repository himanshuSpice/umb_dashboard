package com.spice.service.creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.request.CommentViewRequest;
import com.spice.service.creation.request.CreateCommentRequest;
import com.spice.service.creation.request.EditCommentRequest;
import com.spice.service.creation.request.SrCreateServicRequest;
import com.spice.service.creation.request.SrListRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.SrService;


@RestController
@RequestMapping(value = "/srManagement")
public class SrManagmentController {
	@Autowired
	private SrService srService;
	
	@RequestMapping(value = "/srCreateService", method = RequestMethod.POST)
	public ResponseObj updatesServiceStatus(@RequestBody SrCreateServicRequest srCreateServicRequest,  @RequestHeader(value="userId") String userId) throws Exception{
		return srService.createService(srCreateServicRequest, userId);
	}
	
	@RequestMapping(value = "/srList", method = RequestMethod.POST)
	public ResponseObj srList(@RequestBody SrListRequest srListRequest,  @RequestHeader(value="userId") String userId) throws Exception{
		return srService.srList(srListRequest, userId);
	}
	
	@RequestMapping(value = "/editComment", method = RequestMethod.POST)
	public ResponseObj editComment(@RequestBody EditCommentRequest editCommentRequest,  @RequestHeader(value="userId") String userId) throws Exception{
		return srService.editComment(editCommentRequest, userId);
	}
	
	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public ResponseObj createComment(@RequestBody CreateCommentRequest createCommentRequest,  @RequestHeader(value="userId") String userId) throws Exception{
		return srService.createComment(createCommentRequest, userId);
	}
	
	@RequestMapping(value = "/commentView", method = RequestMethod.POST)
	public ResponseObj commentView(@RequestBody CommentViewRequest commentViewRequest,  @RequestHeader(value="userId") String userId) throws Exception{
		return srService.commentView(commentViewRequest, userId);
	}
	
	

}
