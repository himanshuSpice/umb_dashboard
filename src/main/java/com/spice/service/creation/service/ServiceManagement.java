package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.MenuTreeDao;
import com.spice.service.creation.dao.ServiceManagementDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.request.CancelEditServiceRequest;
import com.spice.service.creation.request.CommitServiceRequest;
import com.spice.service.creation.request.CreateServiceMasterRequest;
import com.spice.service.creation.request.CreateServiceNode;
import com.spice.service.creation.request.CreateServiceRequest;
import com.spice.service.creation.request.EditServiceRequest;
import com.spice.service.creation.request.FetchServiceDetailRequest;
import com.spice.service.creation.request.RevokeRevisionRequest;
import com.spice.service.creation.request.ServiceListRequest;
import com.spice.service.creation.request.ServiceStatus;
import com.spice.service.creation.request.UpdateNodeStatusRequest;
import com.spice.service.creation.request.UpdateServiceDetailRequest;
import com.spice.service.creation.request.UpdateServiceRequest;
import com.spice.service.creation.response.CreateServiceNodeListResponse;
import com.spice.service.creation.response.CreateServiceNodeResponse;
import com.spice.service.creation.response.CreateServiceResponse;
import com.spice.service.creation.response.FetchServiceDetailResponse;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.ServiceListResponse;
import com.spice.service.creation.response.ServiceRevisionResponse;
import com.spice.service.creation.response.SingleServiceNodeReponse;
import com.spice.service.creation.response.ViewEditServiceResponse;

@Component
@Service
public class ServiceManagement {
	
	@Autowired
	private ServiceManagementDao serviceManagementDao;
	
	@Autowired
	private MenuTreeDao treeDao;
	
	public ResponseObj updatesServiceStatus(ServiceStatus serviceStatus)throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.updatesServiceStatus(serviceStatus);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	

	public ResponseObj createService(CreateServiceRequest createServiceRequest,String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.createService(createServiceRequest, userId);
		CreateServiceResponse createServiceResponse = new CreateServiceResponse();
		ResultSet resultSet = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),"Duplicate service code. Please enter other code.", Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			
	         while(resultSet.next()) {
	        	 createServiceResponse.setServiceId(resultSet.getString("service_id"));
	         }
	     	return new ResponseObj(createServiceResponse , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	public ResponseObj createServiceNode(CreateServiceNode createServiceNode, String userId)  throws Exception {
		CreateServiceNodeResponse nodeResponse = new CreateServiceNodeResponse();
		CallableStatement verifyResponses = serviceManagementDao.createServiceNode(createServiceNode, userId);
		ResultSet resultSet = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		   while(resultSet.next()) {
			   nodeResponse.setServiceId(resultSet.getString("node_id"));
		   }
		return new ResponseObj(nodeResponse , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj getSingleServiceNode(String parentId, String serviceId)  throws Exception {
		List<SingleServiceNodeReponse> daoResponse = serviceManagementDao.getSingleServiceNode(parentId, serviceId);
		if(daoResponse.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
			  return new ResponseObj(daoResponse);
		}
	
	
	public ResponseObj createServiceNodeList( List<CreateServiceNode> createServiceNodeList, String userId )  throws Exception {
		CallableStatement verifyResponses = null;
		CreateServiceNodeListResponse listResponse = new CreateServiceNodeListResponse();
		ResultSet set = null;
		Iterator<CreateServiceNode> itr=createServiceNodeList.listIterator(); 
		while(itr.hasNext()) {
		CreateServiceNode node = itr.next();
		verifyResponses = serviceManagementDao.createServiceNode(node, userId);
		set = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) {
			treeDao.deleteNode(createServiceNodeList.get(0).getParentId(), userId, createServiceNodeList.get(0).getServiceId());
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
		}
		while(set.next()) {
			listResponse.setNodeId(set.getString("node_id"));
         }
		return new ResponseObj(listResponse, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
	
	
	public ResponseObj updateService(UpdateServiceRequest updateServiceRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.updateService(updateServiceRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj updateServiceNodeList(List<UpdateServiceRequest> updateServiceRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = null;
		CreateServiceNodeListResponse listResponse = new CreateServiceNodeListResponse();
		Iterator<UpdateServiceRequest> itr=updateServiceRequest.listIterator(); 
		while(itr.hasNext()) {
		UpdateServiceRequest node = itr.next();
		verifyResponses = serviceManagementDao.updateService(node, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) {
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
		}
		return new ResponseObj(listResponse, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
	

	public ResponseObj updateNodeStatus(UpdateNodeStatusRequest updateNodeStatusRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.updateNodeStatus(updateNodeStatusRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj createServiceMaster(CreateServiceMasterRequest createServiceMasterRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.createServiceMaster(createServiceMasterRequest, userId);
		CreateServiceResponse response = new CreateServiceResponse();
		ResultSet resultSet = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			while(resultSet.next()) {
				response.setServiceId(resultSet.getString("service_id"));
		   }
			return new ResponseObj(response, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	
	public ResponseObj commitService(CommitServiceRequest commitServiceRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.commitService(commitServiceRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj editService(EditServiceRequest editServiceRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.editService(editServiceRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj serviceList(ServiceListRequest serviceListRequest, String userId )throws Exception {
		List<ServiceListResponse> viewUserResponses = serviceManagementDao.serviceList(serviceListRequest, userId);
		if(viewUserResponses.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
			
		return new ResponseObj(viewUserResponses);
	}
	
	
	
	public ResponseObj updateServiceDetail(UpdateServiceDetailRequest updateServiceDetailRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.updateServiceDetail(updateServiceDetailRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj cancelEditService(CancelEditServiceRequest cancelEditServiceRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.cancelEditService(cancelEditServiceRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj fetchServiceDetail(FetchServiceDetailRequest fetchServiceDetailRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.fetchServiceDetail(fetchServiceDetailRequest, userId);
		ArrayList<FetchServiceDetailResponse> response = new ArrayList<FetchServiceDetailResponse>();
		ResultSet resultSet = 	verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));

		while(resultSet.next()) {
		response.add(new FetchServiceDetailResponse( resultSet.getString("service_id"), resultSet.getString("service_name"),
				resultSet.getString("service_code"),resultSet.getString("creator_id") , resultSet.getString("parent_service_id"),
				resultSet.getString("scope"),resultSet.getString("dcs") , resultSet.getString("circle"), resultSet.getString("status"),
				resultSet.getString("operator"), resultSet.getString("subscriber_type") ,  resultSet.getString("description") ,
				resultSet.getString("created_on"),resultSet.getString("last_modify_by"),resultSet.getString("last_modified")));
	}
			return new ResponseObj(response, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
	public ResponseObj serviceRevision(String loginId)  throws Exception {
		List<ServiceRevisionResponse> daoResponse = serviceManagementDao.serviceRevision(loginId);
		if(daoResponse.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
			  return new ResponseObj(daoResponse);
		}
	public ResponseObj viewEditService(String loginId)  throws Exception {
		List<ViewEditServiceResponse> daoResponse = serviceManagementDao.viewEditService(loginId);
		if(daoResponse.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
			  return new ResponseObj(daoResponse);
		}
	public ResponseObj revokeRevisionService(RevokeRevisionRequest revokeRevisionRequest, String userId)  throws Exception {
		CallableStatement verifyResponses = serviceManagementDao.revokeRevisionService(revokeRevisionRequest, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus"))) 
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
			return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
			}
	
}