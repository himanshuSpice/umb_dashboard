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

import com.spice.service.creation.request.CopyNodeRequest;
import com.spice.service.creation.request.CutPasteNodeRequest;
import com.spice.service.creation.request.DeleteTreeNodeRequest;
import com.spice.service.creation.response.DbResponse;
import com.spice.service.creation.response.MenuTreeResponse;
import com.spice.service.creation.response.ServiceNameDao;
import com.spice.service.creation.response.VariableListResponse;

@Transactional
@Repository
public class MenuTreeDao {
	
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
	
	public List<MenuTreeResponse> getMenuTreeList(String userId, String parentId) throws Exception {
		String str = "call proc_get_service_list(?,?)";
		return  jdbcTemplateObject.query(str,new Object[] {userId, parentId}, new MenuTreeResponse());
	}
	
	public List<VariableListResponse> getVariableList() throws Exception {
		String str = "select variable_id, variable_name, variable_type, variable_regex, status, created_on, last_modified from tbl_variable_master";
		return  jdbcTemplateObject.query(str,new Object[] {}, new VariableListResponse());
	}
	
	
	public List<DbResponse>  menuTreeList(String serviceId) throws Exception {
		String str = "CALL proc_get_service_nodes_new(?)";
		return jdbcTemplateObject.query(str, new Object[] {serviceId},new DbResponse());
	}
	
	
	public List<DbResponse>  menuTreeListDownload(String serviceId) throws Exception {
		String str = "CALL proc_get_service_nodes_download(?)";
		return jdbcTemplateObject.query(str, new Object[] {serviceId},new DbResponse());
	}
	
	public ServiceNameDao  getServiceName(String serviceId) throws Exception {
		String str = "SELECT service_code FROM tbl_service_master WHERE service_id=?";
		return jdbcTemplateObject.queryForObject(str, new Object[] {serviceId},new ServiceNameDao());
	}
	
	public CallableStatement deleteNode(String nodeId, String userId, String  serviceId) throws Exception {
		String str = "{call proc_delete_node(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_node_id",nodeId); 
	         stmt.setString("in_login_id",userId); 
	         stmt.setString("in_service_id",serviceId); 
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

	public CallableStatement deleteTreeNode(DeleteTreeNodeRequest treeNodeRequest, String userId) throws Exception {
		String str = "{call proc_delete_node_from_tree(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
			 CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_node_id",treeNodeRequest.getNodeId()); 
	         stmt.setString("in_login_id",userId); 
	         stmt.setString("in_service_id",treeNodeRequest.getServiceId()); 
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

	
	public CallableStatement copyNode(CopyNodeRequest copyNodeRequest, String userId) throws Exception {
		String str = "{call proc_copy_paste_node(?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_login_id",userId); 
	         stmt.setString("in_service_id", copyNodeRequest.getInServiceId()); 
	         stmt.setString("in_source_id",copyNodeRequest.getInSourceId());
	         stmt.setString("in_destination_id",copyNodeRequest.getInDestinationId());
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
	
	
	
	public CallableStatement cutPasteNode(CutPasteNodeRequest request, String userId) throws Exception {
		String str = "{call proc_cut_paste_node(?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_login_id",userId); 
	         stmt.setString("in_source_service_id", request.getSourceServiceId()); 
	         stmt.setString("in_source_node_id",request.getSourceNodeId());
	         stmt.setString("in_dest_service_id",request.getDestServiceId());
	         stmt.setString("in_dest_node_id",request.getDestNodeId());
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


