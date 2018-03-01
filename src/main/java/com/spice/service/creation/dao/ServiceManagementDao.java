package com.spice.service.creation.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
import com.spice.service.creation.response.ServiceListResponse;
import com.spice.service.creation.response.ServiceRevisionResponse;
import com.spice.service.creation.response.SingleServiceNodeReponse;
import com.spice.service.creation.response.ViewEditServiceResponse;


@Transactional
@Repository
public class ServiceManagementDao {

	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.username}")
	private String jdbcUserName;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
	
	public CallableStatement updatesServiceStatus(ServiceStatus serviceStatus) throws Exception {
		String str = "{call proc_update_service_status(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
			 CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_service_id", serviceStatus.getServiceId());  
	         stmt.setString("in_status", serviceStatus.getStatus());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR);
	            stmt.execute();
	            return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	
	public CallableStatement createService(CreateServiceRequest createServiceRequest, String userId ) throws Exception {
		
		String str = "{call proc_create_service_master(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_service_name", createServiceRequest.getServiceName());  
	         stmt.setString("in_service_code", createServiceRequest.getServiceCode());
	         stmt.setString("in_creator_id", createServiceRequest.getCreatorId());
	         stmt.setString("in_parent_service_id", createServiceRequest.getParentServiceId());
	         stmt.setString("in_scope", createServiceRequest.getScope());
	         stmt.setString("in_dcs", createServiceRequest.getDcs());
	         stmt.setString("in_circle", createServiceRequest.getCircle());
	         stmt.setString("in_status", createServiceRequest.getStatus());
	         stmt.setString("in_operator", createServiceRequest.getOperator());
	         stmt.setString("in_subscriber_type", createServiceRequest.getSubscriberType());
	         stmt.setString("in_description", createServiceRequest.getDescription());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public List<SingleServiceNodeReponse>  getSingleServiceNode(String parentId, String serviceId) throws Exception {
		String str = "Call proc_get_single_service_node(?,?)";
		return jdbcTemplateObject.query(str, new Object[] {parentId, serviceId},new SingleServiceNodeReponse());
	}

	
	public CallableStatement createServiceNode(CreateServiceNode createServiceNode,String userId) throws Exception {
		String str = "{call proc_create_service_node(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn =null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_parent_id", createServiceNode.getParentId());  
	         stmt.setString("in_status", createServiceNode.getStatus());
	         stmt.setString("in_service_id", createServiceNode.getServiceId());
	         stmt.setString("in_service_code", createServiceNode.getServiceCode());
	         stmt.setString("in_request_text", createServiceNode.getRequestText());
	         stmt.setString("in_response_text", createServiceNode.getResponseText());
	         stmt.setString("in_regex", createServiceNode.getRegex());
	         stmt.setString("in_invalid_response_text", createServiceNode.getInvalidResponseText());
	         stmt.setString("in_invalid_menu_flag", createServiceNode.getInvalidMenuFlag());
	         stmt.setString("in_node_type", createServiceNode.getNodeType());
	         stmt.setString("in_is_header", createServiceNode.getIsHeader());
	         stmt.setString("in_is_footer", createServiceNode.getIsFooter());
	         stmt.setString("in_is_list_element", createServiceNode.getIsListElement());
	         stmt.setString("in_preference", createServiceNode.getPreference());
	         stmt.setString("in_menu_flag", createServiceNode.getMenuFlag());
	         stmt.setString("in_dcs", createServiceNode.getDcs());
	         stmt.setString("in_application_id", createServiceNode.getApplicationId());
	         stmt.setString("in_variable_name", createServiceNode.getVariableName());
	         stmt.setString("in_variable_value", createServiceNode.getVariableValue());
	         stmt.setString("in_switch_service_id", createServiceNode.getSwitchServiceId());
	         stmt.setString("in_switch_service_code", createServiceNode.getServiceCode());
	         stmt.setString("in_circle_id", createServiceNode.getCircleId());
	         stmt.setString("in_operator_id", createServiceNode.getOperatorId());
	         stmt.setString("in_if_clause", createServiceNode.getIfClause());
	         stmt.setString("in_if_value_clause", createServiceNode.getIfValueClause());
	         stmt.registerOutParameter("OutStatus", Types.INTEGER);
	         stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	         stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public CallableStatement updateService(UpdateServiceRequest updateServiceRequest, String userId) throws Exception {
		String str = "{call proc_update_service_node(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_node_id", updateServiceRequest.getNodeId());  
	         stmt.setString("in_parent_id", updateServiceRequest.getParentId());
	         stmt.setString("in_status", updateServiceRequest.getStatus());
	         stmt.setString("in_service_id", updateServiceRequest.getServiceId());
	         stmt.setString("in_service_code", updateServiceRequest.getServiceCode());
	         stmt.setString("in_request_text", updateServiceRequest.getRequestText());
	         stmt.setString("in_response_text", updateServiceRequest.getResponseText());
	         stmt.setString("in_regex", updateServiceRequest.getRegex());
	         stmt.setString("in_invalid_response_text", updateServiceRequest.getInvalidResponseText());
	         stmt.setString("in_invalid_menu_flag", updateServiceRequest.getInvalidMenuFlag());
	         stmt.setString("in_node_type", updateServiceRequest.getNodeType());
	         stmt.setString("in_is_header", updateServiceRequest.getIsHeader());
	         stmt.setString("in_is_footer", updateServiceRequest.getIsFooter());
	         stmt.setString("in_is_list_element", updateServiceRequest.getIsListElement());
	         stmt.setString("in_preference", updateServiceRequest.getPreference());
	         stmt.setString("in_menu_flag", updateServiceRequest.getMenuFlag());
	         stmt.setString("in_dcs", updateServiceRequest.getDcs());
	         stmt.setString("in_application_id", updateServiceRequest.getApplicationId());
	         stmt.setString("in_variable_name", updateServiceRequest.getVariableName());
	         stmt.setString("in_variable_value", updateServiceRequest.getVariableValue());
	         stmt.setString("in_switch_service_id", updateServiceRequest.getSwitchServiceId());
	         stmt.setString("in_switch_service_code", updateServiceRequest.getSwitchServiceCode());
	         stmt.setString("in_circle_id", updateServiceRequest.getCircleId());
	         stmt.setString("in_operator_id", updateServiceRequest.getOperatorId());
	         stmt.setString("in_if_clause", updateServiceRequest.getIfClause());
	         stmt.setString("in_if_value_clause", updateServiceRequest.getIfValueClause());
	         stmt.setString("in_login_id", userId);
	            stmt.registerOutParameter("OutStatus", Types.VARCHAR); 
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER); 
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	        	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public CallableStatement updateNodeStatus(UpdateNodeStatusRequest updateNodeStatusRequest, String userId) throws Exception {
		String str = "{call proc_update_node_status(?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_node_id", updateNodeStatusRequest.getNodeId());  
	         stmt.setString("in_status", updateNodeStatusRequest.getStatus());
	         stmt.setString("in_service_id", updateNodeStatusRequest.getServiceId());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public CallableStatement createServiceMaster(CreateServiceMasterRequest createServiceMasterRequest, String userId) throws Exception {
		String str = "{call proc_create_service_master(?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setInt("in_creation_mode", Integer.parseInt(createServiceMasterRequest.getCreationMode()));  
	         stmt.setString("in_source_service_id", createServiceMasterRequest.getSourceServiceId());
	         stmt.setString("in_parent_service_id", createServiceMasterRequest.getParentServiceId());
	         stmt.setString("in_service_name", createServiceMasterRequest.getServiceName());
	         stmt.setString("in_service_code", createServiceMasterRequest.getServiceCode());
	         stmt.setString("in_description", createServiceMasterRequest.getDescription());
	         stmt.setString("in_tree_type", createServiceMasterRequest.getTreeType());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	
	
	public CallableStatement commitService(CommitServiceRequest commitServiceRequest, String userId) throws Exception {
		String str = "{call proc_commit_service(?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_version_type", commitServiceRequest.getVersionType());  
	         stmt.setString("in_service_id", commitServiceRequest.getServiceId());
	         stmt.setString("in_remarks", commitServiceRequest.getRemarks());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	
	public CallableStatement editService(EditServiceRequest editServiceRequest, String userId) throws Exception {
		String str = "{call proc_edit_service(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_service_id", editServiceRequest.getServiceId());  
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public List<ServiceListResponse>  serviceList(ServiceListRequest serviceListRequest, String userId) throws Exception {
		String str = "call proc_get_service_list(?,?)";
		return jdbcTemplateObject.query(str, new Object[] {userId, serviceListRequest.getParentId()},new ServiceListResponse());
	}
	
	
	public CallableStatement updateServiceDetail(UpdateServiceDetailRequest updateServiceDetailRequest, String userId) throws Exception {
		String str = "{call proc_update_service_detail(?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_description", updateServiceDetailRequest.getDescription());  
	         stmt.setString("in_service_id", updateServiceDetailRequest.getServiceId());  
	         stmt.setString("in_service_name", updateServiceDetailRequest.getServiceName());  
	         stmt.setString("in_status", updateServiceDetailRequest.getStatus());  
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public CallableStatement cancelEditService(CancelEditServiceRequest cancelEditServiceRequest, String userId) throws Exception {
		String str = "{call proc_cancel_edit_service(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_service_id", cancelEditServiceRequest.getServiceId());  
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	
	public CallableStatement fetchServiceDetail(FetchServiceDetailRequest fetchServiceDetailRequest, String userId) throws Exception {
		String str = "{call proc_fetch_service_detail(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_service_id", fetchServiceDetailRequest.getServiceId());  
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	public List<ServiceRevisionResponse>  serviceRevision(String loginId) throws Exception {
		String str = "Call proc_view_service_revision_history(?)";
		return jdbcTemplateObject.query(str, new Object[] {loginId},new ServiceRevisionResponse());
	}
	
	public List<ViewEditServiceResponse>  viewEditService(String loginId) throws Exception {
		String str = "Call proc_view_edit_service_history(?)";
		return jdbcTemplateObject.query(str, new Object[] {loginId},new ViewEditServiceResponse());
	}
	
	public CallableStatement revokeRevisionService(RevokeRevisionRequest revokeRevisionRequest, String userId) throws Exception {
		String str = "{call proc_revoke_revision_service(?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl, jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("in_login_id", userId);
	         stmt.setString("in_service_id", revokeRevisionRequest.getServiceId()); 
	         stmt.setString("in_version_major", revokeRevisionRequest.getVersionMajor()); 
	         stmt.setString("in_version_minor", revokeRevisionRequest.getVersionMinor()); 
	         stmt.setString("in_remarks", revokeRevisionRequest.getRemarks()); 
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR); 
	         stmt.execute();
	         return stmt;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		finally {
		    if (conn != null) {
		        try {
		        	conn.close();
		        } catch (SQLException e) {  }
		    }
		}
		return null;
		}
	
	
}
