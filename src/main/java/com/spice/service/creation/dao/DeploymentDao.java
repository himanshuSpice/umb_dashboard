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

import com.spice.service.creation.request.AddMsisdnRequest;
import com.spice.service.creation.request.CancelDeploymentRequest;
import com.spice.service.creation.request.DeployUatRequest;
import com.spice.service.creation.request.DeployViewLogsRequest;
import com.spice.service.creation.response.ViewLogsResponse;
import com.spice.service.creation.response.ViewMsisdnResponse;

@Transactional
@Repository
public class DeploymentDao {
	
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.username}")
	private String jdbcUserName;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;

	
	public CallableStatement addMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId) throws Exception {
		String str = "{call proc_test_msisdn_add(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_msisdn",addMsisdnRequest.getMsisdn()); 
	         stmt.setString("in_login_id",userId); 
	         stmt.setString("in_service_id",addMsisdnRequest.getServiceId()); 
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
	
	public CallableStatement updateMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId) throws Exception {
		String str = "{call proc_test_msisdn_update(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_msisdn",addMsisdnRequest.getMsisdn()); 
	         stmt.setString("in_login_id",userId);
	         stmt.setString("in_service_id",addMsisdnRequest.getServiceId()); 
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
	
	public CallableStatement deleteMsisdn(AddMsisdnRequest addMsisdnRequest,String  userId) throws Exception {
		String str = "{call proc_test_msisdn_delete(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_msisdn",addMsisdnRequest.getMsisdn()); 
	         stmt.setString("in_login_id",userId);
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
	public List<ViewMsisdnResponse> viewMsisdn(String userId) throws Exception {
		String str = "call proc_test_msisdn_view(?)";
		return  jdbcTemplateObject.query(str,new Object[] {userId}, new ViewMsisdnResponse());
	}
	
	public List<ViewLogsResponse> deployViewLogs(DeployViewLogsRequest deployViewLogsRequest, String userId) throws Exception {
		String str = "call proc_service_deploy_view_logs(?,?,?,?)";
		return  jdbcTemplateObject.query(str,new Object[] {userId, deployViewLogsRequest.getDeploymentType(),deployViewLogsRequest.getFromDate(), deployViewLogsRequest.getToDate() }, new ViewLogsResponse());
	}
	
	public CallableStatement deployUat(DeployUatRequest deployUatRequest,String  userId) throws Exception {
		String str = "{call proc_service_deploy_uat(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_service_id",deployUatRequest.getServiceId()); 
	         stmt.setString("in_login_id",userId);
	         stmt.setString("in_deployment_date",deployUatRequest.getDeploymentDate());
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
	
	public CallableStatement deployLive(DeployUatRequest deployUatRequest,String  userId) throws Exception {
		String str = "{call proc_service_deploy_live(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_service_id",deployUatRequest.getServiceId()); 
	         stmt.setString("in_login_id",userId);
	         stmt.setString("in_deployment_date",deployUatRequest.getDeploymentDate());
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
	
	
	public CallableStatement cancelDeployment(CancelDeploymentRequest request,String  userId) throws Exception {
		String str = "{call proc_cancel_deployment(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_login_id",userId);
	         stmt.setString("in_deployment_id",request.getDeploymentId());
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
