package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.MenuTreeDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.request.CopyNodeRequest;
import com.spice.service.creation.request.CutPasteNodeRequest;
import com.spice.service.creation.request.DeleteTreeNodeRequest;
import com.spice.service.creation.response.DbResponse;
import com.spice.service.creation.response.MenuTreeResponse;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.ServiceNameDao;
import com.spice.service.creation.response.TreeResponse;
import com.spice.service.creation.response.VariableListResponse;
import com.spice.service.creation.utility.DynaminTree;

@Component
@Service
public class MenuTreeService {
	
	@Autowired
	private DynaminTree dynaminTree; 
	
	@Autowired
	private MenuTreeDao menuTreeDao;
	
	public ResponseObj getMenuTreeList(String userId, String parentId)  throws Exception {
		List<MenuTreeResponse> menuTreeResponses = menuTreeDao.getMenuTreeList(userId, parentId);
		if(menuTreeResponses.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
		return new ResponseObj(menuTreeResponses);
	}
	
	
	public ResponseObj getVariableList()  throws Exception {
		List<VariableListResponse> variableListResponse = menuTreeDao.getVariableList();
		if(variableListResponse.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
		return new ResponseObj(variableListResponse);
	}
	
		public ResponseObj menuTreeList(String serviceId)  throws Exception {
			ServiceNameDao nameDao =   menuTreeDao.getServiceName(serviceId);
			List<DbResponse> response = menuTreeDao.menuTreeList(serviceId);;
			TreeResponse treeResponse =  dynaminTree.getTree(nameDao.getServiceName(),"0",response);
		  return new ResponseObj(treeResponse);
		 }
		
		
		public void menuTreeListCsv(String serviceId, HttpServletRequest request, HttpServletResponse response)  throws Exception {
			ServiceNameDao nameDao =   menuTreeDao.getServiceName(serviceId);
			List<DbResponse> dbresponse = menuTreeDao.menuTreeListDownload(serviceId);;
			 dynaminTree.getTreeCsv(nameDao.getServiceName(),"0",dbresponse, request, response);
		 }
		
		public ResponseObj deleteNode(String nodeId, String userId, String serviceId )  throws Exception {
			CallableStatement verifyResponses = menuTreeDao.deleteNode(nodeId, userId, serviceId);
			if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
				throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			
			return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
		
		public ResponseObj deleteTreeNode(DeleteTreeNodeRequest treeNodeRequest, String userId)  throws Exception {
			CallableStatement verifyResponses = menuTreeDao.deleteTreeNode(treeNodeRequest, userId);
			if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
				throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			
			return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
		
		
		public ResponseObj copyNode(CopyNodeRequest copyNodeRequest, String userId)  throws Exception {
			CallableStatement verifyResponses = menuTreeDao.copyNode(copyNodeRequest, userId);
			if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
				throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			
			return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
		
		
		public ResponseObj cutPasteNode(CutPasteNodeRequest request, String userId)  throws Exception {
			CallableStatement verifyResponses = menuTreeDao.cutPasteNode(request, userId);
			if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
				throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			
			return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
	}
