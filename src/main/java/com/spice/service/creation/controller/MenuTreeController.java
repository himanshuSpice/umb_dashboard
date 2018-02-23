package com.spice.service.creation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.request.CopyNodeRequest;
import com.spice.service.creation.request.DeleteTreeNodeRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.MenuTreeService;

@RestController
@RequestMapping(value = "/menuTree")
public class MenuTreeController {
	
	@Autowired
	private MenuTreeService menuTreeService;
	
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public ResponseObj getMenuTreeList( @RequestHeader(value="userId") String userId, @RequestParam(value="parentId") String parentId) throws Exception {
		return menuTreeService.getMenuTreeList(userId, parentId);
	}
	
	@RequestMapping(value = "/variableList", method = RequestMethod.GET)
	public ResponseObj getVariableList() throws Exception {
		return menuTreeService.getVariableList();
	}
	
	@RequestMapping(value = "/getMenuTree", method = RequestMethod.GET)
	public ResponseObj menuTreeList(@RequestParam("serviceId") String serviceId) throws Exception {
		return menuTreeService.menuTreeList(serviceId);
	}
	
	@RequestMapping(value = "/getMenuTreeCsv", method = RequestMethod.GET)
	public void getMenuTreeCsv(@RequestParam("serviceId") String serviceId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 menuTreeService.menuTreeListCsv(serviceId, request, response);
	}
	
	@RequestMapping(value = "/deleteNode", method = RequestMethod.GET)
	public ResponseObj deleteNode(@RequestParam("nodeId") String nodeId, @RequestHeader(value="userId") String userId, @RequestParam("serviceId") String serviceId ) throws Exception {
		System.getProperty("profileId");
		return menuTreeService.deleteNode(nodeId, userId, serviceId);
	}
	
	@RequestMapping(value = "/deleteTreeNode", method = RequestMethod.POST)
	public ResponseObj deleteTreeNode(@RequestBody DeleteTreeNodeRequest treeNodeRequest ,  @RequestHeader(value="userId") String userId ) throws Exception {
		return menuTreeService.deleteTreeNode(treeNodeRequest , userId);
	}
	
	
	@RequestMapping(value = "/copyNode", method = RequestMethod.POST)
	public ResponseObj copyNode(@RequestBody CopyNodeRequest copyNodeRequest,   @RequestHeader(value="userId") String userId) throws Exception {
		return menuTreeService.copyNode(copyNodeRequest, userId);
	}
}
