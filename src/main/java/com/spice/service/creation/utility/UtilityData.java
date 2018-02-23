package com.spice.service.creation.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

public class UtilityData {
	
	private final static String salt="Dbbwjvj$%)GE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";
	//private static final String ALPHA_NUM = "0123456789";

	public String getMD5(String input) throws NoSuchAlgorithmException {
		 		input=input+salt;
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	    }
	 

	 
	/*	public static String getAlphaNumeric(int len) {
			  StringBuffer sb = new StringBuffer(len);
			  for (int i = 0; i < len; i++) {
			  int ndx = (int) (Math.random() * ALPHA_NUM.length());
			  sb.append(ALPHA_NUM.charAt(ndx));
			  }
			  return sb.toString();
			  }
		*/
		 public static String getMachineIp(){
		        try {
		            return String.valueOf(InetAddress.getLocalHost());
		        } catch (UnknownHostException ex) {
		            ex.printStackTrace();
		        }
				return null;
		    }
		 
		 public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath ) throws IOException {
			 File file = new File(filePath);
	         if(!file.exists()){
	             String errorMessage = "Sorry. The file you are looking for does not exist";
	             OutputStream outputStream = response.getOutputStream();
	             outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	             outputStream.close();
	             return;
	         }
	         String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	         if(mimeType==null){
	             mimeType = "application/csv";
	         }
	         response.setContentType(mimeType);
	         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	         response.setContentLength((int)file.length());
	         InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
	         FileCopyUtils.copy(inputStream, response.getOutputStream());
	         if(file.exists()){
	         	file.delete();
	         }
		 }
}
