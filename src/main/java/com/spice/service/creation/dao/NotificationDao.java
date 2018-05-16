package com.spice.service.creation.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class NotificationDao {
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.username}")
	private String jdbcUserName;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	public CallableStatement clearNotification(String loginId,	String userId ) {
			String str = "{call proc_clear_notification(?,?,?,?,?)}";
			Connection conn = null;
			try  {
				 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
	            CallableStatement stmt=conn.prepareCall(str); 
		         stmt.setString("in_login_id",userId); 
		         stmt.setString("in_user_id",loginId); 
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
	
	public CallableStatement updateReadNotification(String notificationId,	String loginId ) {
		String str = "{call proc_update_read_notification(?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_user_id",loginId); 
	         stmt.setString("in_notification_id",notificationId); 
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
	
	
	public CallableStatement viewNotification(String loginId, String flag, String status) {
		String str = "{call proc_view_notification(?,?,?,?,?,?,?)}";
		Connection conn = null;
		try  {
			 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
            CallableStatement stmt=conn.prepareCall(str); 
	         stmt.setString("in_user_id",loginId);
	         stmt.setString("in_flag",flag);
	         stmt.setString("in_status",status);
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
