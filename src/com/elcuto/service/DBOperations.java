package com.elcuto.service;

import java.sql.Connection;
import java.sql.Statement;

public class DBOperations {
	 
     public static void deleteRecord(int id) {
    	 try {
    		 
    		 DBConnection conn = DBConnection.getInstance();
			 Connection mycon = conn.getConnection();
			 Statement stmt = mycon.createStatement();
			 String SQL = "DELETE FROM elc3rdPartySmsService.dbo.New3ppSubscriptionPending WHERE Id='"+id+"';";
	         stmt.executeUpdate(SQL);
    	 }catch(Exception e) {
    		 e.printStackTrace();    		 
    	 }
    	 
     }
}
