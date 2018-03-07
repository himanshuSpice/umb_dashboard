package com.spice.service.creation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.request.CancelEditServiceRequest;
import com.spice.service.creation.request.CommitServiceRequest;
import com.spice.service.creation.request.CreateServiceMasterRequest;
import com.spice.service.creation.request.CreateServiceNode;
import com.spice.service.creation.request.CreateServiceRequest;
import com.spice.service.creation.request.EditServiceRequest;
import com.spice.service.creation.request.FetchServiceDetailRequest;
import com.spice.service.creation.request.LinkServiceNodeRequest;
import com.spice.service.creation.request.RevokeRevisionRequest;
import com.spice.service.creation.request.ServiceListRequest;
import com.spice.service.creation.request.ServiceStatus;
import com.spice.service.creation.request.UpdateNodeStatusRequest;
import com.spice.service.creation.request.UpdateServiceDetailRequest;
import com.spice.service.creation.request.UpdateServiceRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.ServiceManagement;


@RestController
@RequestMapping(value = "/serviceManagement")
public class ServiceManagementController {
	
	@Autowired
	private ServiceManagement serviceManagement;
	
	@RequestMapping(value = "/updateServiceStatus", method = RequestMethod.POST)
	public ResponseObj updatesServiceStatus(@RequestBody ServiceStatus serviceStatus ) throws Exception{
		return serviceManagement.updatesServiceStatus(serviceStatus);
	}
	
	// not in use
	@RequestMapping(value = "/createService", method = RequestMethod.POST)
	public ResponseObj createService(@Valid @RequestBody CreateServiceRequest createServiceRequest,  @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.createService(createServiceRequest, userId);
	}
	
	@RequestMapping(value = "/createServiceNode", method = RequestMethod.POST)
	public ResponseObj createServiceNode(@RequestBody CreateServiceNode createServiceNode,  @RequestHeader(value="userId", required=false) String userId) throws Exception {
		return serviceManagement.createServiceNode(createServiceNode, userId);
	}
	@RequestMapping(value = "/getSingleNodeData", method = RequestMethod.GET)
	public ResponseObj getSingleServiceNode(@RequestParam("parentId") String parentId, @RequestParam("serviceId") String serviceId ) throws Exception {
		return serviceManagement.getSingleServiceNode(parentId, serviceId);
	}
	
	@RequestMapping(value = "/createServiceNodeList", method = RequestMethod.POST)
	public ResponseObj createServiceNodeList(@RequestBody List<CreateServiceNode> CreateServiceNodeList ,  @RequestHeader(value="userId", required=false) String userId) throws Exception {
		System.out.println("CreateServiceNodeList  "+CreateServiceNodeList);
		return serviceManagement.createServiceNodeList(CreateServiceNodeList, userId);
	}
	

	@RequestMapping(value = "/updateServiceNode", method = RequestMethod.POST)
	public ResponseObj menuTreeList(@RequestBody UpdateServiceRequest updateServiceRequest,  @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.updateService(updateServiceRequest,userId);
	}
	
	@RequestMapping(value = "/updateServiceNodeList", method = RequestMethod.POST)
	public ResponseObj updateServiceNodeList(@RequestBody List<UpdateServiceRequest>  updateServiceRequest,  @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.updateServiceNodeList(updateServiceRequest,userId);
	}
	
	
	
	@RequestMapping(value = "/updateNodeStatus", method = RequestMethod.POST)
	public ResponseObj updateNodeStatus(@RequestBody UpdateNodeStatusRequest updateNodeStatusRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.updateNodeStatus(updateNodeStatusRequest, userId);
	}

	@RequestMapping(value = "/createServiceMaster", method = RequestMethod.POST)
	public ResponseObj createServiceMaster(@RequestBody CreateServiceMasterRequest createServiceMasterRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.createServiceMaster(createServiceMasterRequest, userId);
	}
	
	@RequestMapping(value = "/commitService", method = RequestMethod.POST)
	public ResponseObj commitService(@RequestBody CommitServiceRequest commitServiceRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.commitService(commitServiceRequest, userId);
	}
	
	@RequestMapping(value = "/editService", method = RequestMethod.POST)
	public ResponseObj editService(@RequestBody EditServiceRequest editServiceRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.editService(editServiceRequest, userId);
	}
	
	@RequestMapping(value = "/serviceList", method = RequestMethod.POST)
	public ResponseObj serviceList(@RequestBody ServiceListRequest serviceListRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.serviceList(serviceListRequest, userId);
	}
	
	
	@RequestMapping(value = "/updateServiceDetail", method = RequestMethod.POST)
	public ResponseObj updateService(@RequestBody UpdateServiceDetailRequest updateServiceDetailRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.updateServiceDetail(updateServiceDetailRequest, userId);
	}
	
	@RequestMapping(value = "/canceEditService", method = RequestMethod.POST)
	public ResponseObj cancelEditService(@RequestBody CancelEditServiceRequest cancelEditServiceRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.cancelEditService(cancelEditServiceRequest, userId);
	}
	
	@RequestMapping(value = "/fetchServiceDetail", method = RequestMethod.POST)
	public ResponseObj fetchServiceDetail(@RequestBody FetchServiceDetailRequest fetchServiceDetailRequest, @RequestHeader(value="userId") String userId) throws Exception {
		return serviceManagement.fetchServiceDetail(fetchServiceDetailRequest, userId);
	}
	

	@RequestMapping(value = "/serviceRevision", method = RequestMethod.GET)
	public ResponseObj serviceRevision(@RequestHeader(value="userId") String loginId) throws Exception {
		return serviceManagement.serviceRevision(loginId);
	}
	
	@RequestMapping(value = "/viewEditService", method = RequestMethod.GET)
	public ResponseObj viewEditService(@RequestHeader(value="userId") String loginId) throws Exception {
		return serviceManagement.viewEditService(loginId);
	}
	
	@RequestMapping(value = "/revokeRevision", method = RequestMethod.POST)
	public ResponseObj revokeRevisionService(@RequestBody RevokeRevisionRequest revokeRevisionRequest , @RequestHeader(value="userId") String loginId) throws Exception {
		return serviceManagement.revokeRevisionService(revokeRevisionRequest, loginId);
	}
	
	@RequestMapping(value = "/linkServiceNode", method = RequestMethod.POST)
	public ResponseObj linkServiceNode(@RequestBody LinkServiceNodeRequest linkServiceNodeRequest , @RequestHeader(value="userId") String loginId) throws Exception {
		return serviceManagement.linkServiceNode(linkServiceNodeRequest, loginId);
	}
	
	
}
