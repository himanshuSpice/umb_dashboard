package com.spice.service.creation.utility;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.csvreader.CsvWriter;
import com.spice.service.creation.dao.MenuTreeDao;
import com.spice.service.creation.response.DbResponse;
import com.spice.service.creation.response.TreeResponse;
@Component
@Service
public class DynaminTree {
	
	@Autowired
	private MenuTreeDao menuTreeDao;
	
	 public static int countSpace = 0;
	public  TreeResponse getTree(String name,String id, List<DbResponse> dbResponses) throws Exception {
		 return new TreeResponse("0",name, "1","0","0","1","0","1", getParent(id, dbResponses));
	}
	
	public  static List<TreeResponse> getParent(String id,List<DbResponse> dbResponses) {
		List<TreeResponse> parentResponses = new ArrayList<TreeResponse>();
		Iterator<DbResponse> iterator = dbResponses.iterator();
		while(iterator.hasNext()) {
			DbResponse requestTree = iterator.next();
			if(id.equals(requestTree.getParentId())) {
				parentResponses.add(new  TreeResponse(requestTree.getNodeId(), requestTree.getResponseText(),requestTree.getEditable(),requestTree.getSwitchServiceId(), requestTree.getCopy(),  requestTree.getDelete(),requestTree.getChange_status(), requestTree.getStatus(), getParent(requestTree.getNodeId(), dbResponses)));
			}
		}
		return parentResponses;
	}
	
	public   void getTreeCsv(String name,String id, List<DbResponse> dbResponses, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/");
		 String fileName = dataDirectory+"Menu_"+name+".csv";
		 File file = new File(fileName);
			CsvWriter csvOutput = new CsvWriter(new FileWriter(file, true), ',');
			csvOutput.write(name);
			csvOutput.endRecord();
			 getParentCsv(id, dbResponses, csvOutput);
			 csvOutput.close();
			 UtilityData.downloadFile(request,  response, fileName);
			 if(file.exists()){
				 file.delete();
			 }

	}
	
	public  List<TreeResponse> getParentCsv(String id,List<DbResponse> dbResponses,CsvWriter csvOutput) throws Exception  {
		List<TreeResponse> parentResponses = new ArrayList<TreeResponse>();
		Iterator<DbResponse> iterator = dbResponses.iterator();
		while(iterator.hasNext()) {
			DbResponse requestTree = iterator.next();
			if(id.equals(requestTree.getParentId())) {
				if(Integer.parseInt(requestTree.getSwitchServiceId())>0) {
					getTreeOfTree(requestTree.getSwitchServiceId(), csvOutput);
				}
				countSpace = countSpace+1;
				if("0".equalsIgnoreCase(requestTree.getSwitchServiceId())) {
				for(int i = 0;i<countSpace;i++) {
	    			csvOutput.write("");
	    		}
				csvOutput.write(requestTree.getResponseText());
				csvOutput.endRecord();
				}
				parentResponses.add(new  TreeResponse(requestTree.getNodeId(), requestTree.getResponseText(),requestTree.getEditable(), requestTree.getCopy(), requestTree.getDelete(),requestTree.getSwitchServiceId(),requestTree.getChange_status(), requestTree.getStatus(), getParentCsv(requestTree.getNodeId(), dbResponses, csvOutput)));
				countSpace = countSpace-1;
			}
		}
		return parentResponses;
	}
	
	public  void  getTreeOfTree(String switchServiceId, CsvWriter csvOutput) throws Exception {
		List<DbResponse> responses = menuTreeDao.menuTreeListDownload(switchServiceId);
		 getParentCsv("0", responses, csvOutput);
	}
}