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

import com.spice.service.creation.request.ChangePasswordRequest;
import com.spice.service.creation.request.CreateUserAccount;
import com.spice.service.creation.request.LoginRequest;
import com.spice.service.creation.request.ResetPasswordRequest;
import com.spice.service.creation.request.UpdateUserAccountRequest;
import com.spice.service.creation.request.UpdateUserStatus;
import com.spice.service.creation.request.ViewUserRequest;
import com.spice.service.creation.response.UserDetailsResponse;
import com.spice.service.creation.response.ViewUserResponse;

@Transactional
@Repository
public class UserManagementDao {
	
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
	
	public CallableStatement verifyUser(LoginRequest loginRequest) throws Exception {
		final String str = "{call proc_user_authenticate(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("InUserName", loginRequest.getUserName());  
	         stmt.setString("InEnPassword", loginRequest.getPassword());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR);
	            stmt.registerOutParameter(6, Types.INTEGER);
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
	
	public CallableStatement changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
		String str = "{call proc_change_password(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_userid", changePasswordRequest.getUserId());  
	         stmt.setString("in_old_password", changePasswordRequest.getOldPassword());
	         stmt.setString("in_password", changePasswordRequest.getPassword());
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
	public CallableStatement createUserAccount( CreateUserAccount  CreateUserAccount) throws Exception {
		String str = "{call proc_create_user_account(?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("InCreatedById", CreateUserAccount.getCreatedById());  
	         stmt.setString("InUserName", CreateUserAccount.getUserName());
	         stmt.setString("InPassword", CreateUserAccount.getPassword());
	         stmt.setString("InEmail", CreateUserAccount.getEmail());
	         stmt.setString("InMsisdn", CreateUserAccount.getMsisdn());
	         stmt.setString("InUserIp", CreateUserAccount.getUserIp());
	         stmt.setString("In_user_mngt_permission", CreateUserAccount.getUserMngtPermission());
	         stmt.setString("In_service_mngt_permission", CreateUserAccount.getServiceMngtPermission());
	         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
	            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
	            stmt.registerOutParameter("OutDesc", Types.VARCHAR);
	            stmt.registerOutParameter("OutUserMasterId", Types.INTEGER);
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
	
	
	public CallableStatement updateUserStatus(UpdateUserStatus  updateUserStatus) throws Exception {
		String str = "{call proc_update_user_status(?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_username", updateUserStatus.getUserName());  
	         stmt.setString("in_status", updateUserStatus.getStatus());
	         stmt.setString("In_user_mngt_permission", updateUserStatus.getUserMngtPermission());
	         stmt.setString("In_service_mngt_permission", updateUserStatus.getServiceMngtPermission());
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
	
	public List<UserDetailsResponse> userDetails() throws Exception {
		String str = "call proc_get_user_detail()";
	return jdbcTemplateObject.query(str,new Object[] {}, new UserDetailsResponse());
	}
	
	public CallableStatement updateUserAccount(UpdateUserAccountRequest  updateUserAccountRequest, String userId) throws Exception {
		String str = "{call proc_update_user_account(?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("InUserId", updateUserAccountRequest.getUserId());
            stmt.setString("InUserName", updateUserAccountRequest.getUserName());
            stmt.setString("InUserType", updateUserAccountRequest.getUserType());
	        stmt.setString("InEmail", updateUserAccountRequest.getEmail());
	        stmt.setString("InMsisdn", updateUserAccountRequest.getMsisdn());
	        stmt.setString("In_user_mngt_permission", updateUserAccountRequest.getUserMngtPermission());
	        stmt.setString("In_service_mngt_permission", updateUserAccountRequest.getPassword());
	        stmt.setString("InPassword", updateUserAccountRequest.getPassword());
	        stmt.setString("InLoginId", userId);
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
	
	
	public List<ViewUserResponse>  viewUser(ViewUserRequest  viewUserRequest, String userId) throws Exception {
		String str = "call proc_get_view_user(?,?)";
		return jdbcTemplateObject.query(str, new Object[] {userId, viewUserRequest.getUserType()},new ViewUserResponse());
	}
	
	
	public CallableStatement resetPasswordRequest(ResetPasswordRequest resetPasswordRequest, String userId) throws Exception {
		String str = "{call Proc_reset_password(?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
            stmt.setString("Inlogin_id", userId);
            stmt.setString("in_user_id", resetPasswordRequest.getUserId());
            stmt.setString("InPassword", resetPasswordRequest.getPassword());
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
