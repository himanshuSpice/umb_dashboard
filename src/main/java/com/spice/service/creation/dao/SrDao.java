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

import com.spice.service.creation.request.CommentViewRequest;
import com.spice.service.creation.request.CreateCommentRequest;
import com.spice.service.creation.request.EditCommentRequest;
import com.spice.service.creation.request.SrCreateServicRequest;
import com.spice.service.creation.request.SrListRequest;
import com.spice.service.creation.response.SrListResponse;
import com.spice.service.creation.response.ViewCommentResponse;

@Transactional
@Repository
public class SrDao {
	
		
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
		
		
		public CallableStatement createService(SrCreateServicRequest srCreateServicRequest, String UserId) throws Exception {
			String str = "{call proc_create_service_request(?,?,?,?,?,?,?,?,?,?,?)}";
			Connection conn = null;
			try  {
				 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
				 CallableStatement stmt=conn.prepareCall(str); 
		         stmt.setString("InSRId", srCreateServicRequest.getSrId());  
		         stmt.setString("InName", srCreateServicRequest.getName());
		         stmt.setString("InDesc", srCreateServicRequest.getDesc());
		         stmt.setString("InTitle", srCreateServicRequest.getTitle());;
		         stmt.setString("InAssignee", srCreateServicRequest.getAssignee());
		         stmt.setString("InTargetDate", srCreateServicRequest.getTargetDate());
		         stmt.setString("InStatus", srCreateServicRequest.getInStatus());
		         stmt.setString("InLoginId", UserId);
			         stmt.registerOutParameter("OutStatus", Types.VARCHAR);
		            stmt.registerOutParameter("OutResponseCode", Types.INTEGER	);
		            stmt.registerOutParameter("OutDesc", Types.VARCHAR);
		           // stmt.registerOutParameter("OutSRId", Types.INTEGER);
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
		
		public List<SrListResponse>  srList(SrListRequest srListRequest, String userId) throws Exception {
			String str = "call proc_get_sr_list(?,?)";
			return jdbcTemplateObject.query(str, new Object[] {userId, srListRequest.getSrid()},new SrListResponse());
		}
		
		
		public CallableStatement editComment(EditCommentRequest editCommentRequest, String UserId) throws Exception {
			String str = "{call proc_sr_edit_comment(?,?,?,?,?,?,?)}";
			Connection conn = null;
			try  {
				 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
				 CallableStatement stmt=conn.prepareCall(str); 
		         stmt.setString("in_id", editCommentRequest.getId());  
		         stmt.setString("in_status", editCommentRequest.getStatus());
		         stmt.setString("in_login_id", UserId);
		         stmt.setString("in_comment", editCommentRequest.getComment());
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
		
		public CallableStatement createComment(CreateCommentRequest createCommentRequest, String UserId) throws Exception {
			String str = "{call proc_sr_create_comment(?,?,?,?,?,?)}";
			Connection conn = null;
			try  {
				 conn = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword); 
				 CallableStatement stmt=conn.prepareCall(str); 
				 stmt.setString("in_service_req_id", createCommentRequest.getServiceReqId());
				 stmt.setString("in_comment", createCommentRequest.getComment());
		         stmt.setString("in_login_id", UserId);
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
		public List<ViewCommentResponse> commentView(CommentViewRequest commentViewRequest, String UserId) throws Exception {
			String str = "call proc_sr_comment_view(?,?)";
			return jdbcTemplateObject.query(str, new Object[] {UserId, commentViewRequest.getServiceReqId()},new ViewCommentResponse());
		
			}
}

