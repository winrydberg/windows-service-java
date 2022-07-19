package com.elcuto.service;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.http.HttpResponse;

public class MyService {
	/**
	    * Single static instance of the service class
	    */
	   private static MyService serviceInstance = new MyService();
	   /**
	    * Flag to know if this service
	    * instance has been stopped.
	    */
	   private boolean stopped = false;
		
	   /**
	    * Static method called by prunsrv to start/stop
	    * the service.  Pass the argument "start"
	    * to start the service, and pass "stop" to
	    * stop the service.
	 * @throws SQLException 
	    */
	   public static void main(String args[]) {
	      String cmd = "start";
	      if(args.length > 0) {
	         cmd = args[0];
	      }
		
	      if("start".equals(cmd)) {
	    	  try {
	    		  serviceInstance.start();
	    	  }catch(Exception e) {
	    		  System.out.println("Exception"+e);
	    	  }
	         
	      }
	      else {
	         serviceInstance.stop();
	      }
	   }

	
		
		
	   /**
	    * Start this service instance
	    * @throws SQLException 
	    */
	   public void start() throws SQLException {
		
	      stopped = false;
			
	      System.out.println("My Service Started " + new java.util.Date());

	    	DBConnection conn = DBConnection.getInstance();
	    	
			
	      while(!stopped) {
	         System.out.println("My Service Executing " + new java.util.Date());
	         try {
					Connection mycon = conn.getConnection();
					Statement stmt = mycon.createStatement();
					String SQL = "SELECT top 1 * FROM elc3rdPartySmsService.dbo.New3ppSubscriptionPending;";
		            ResultSet rs =  stmt.executeQuery(SQL);
		            
		            //loop over resultset
		            while (rs.next()) {
		         
		                RequestData newSubscription = new RequestData();
		                newSubscription.setId(Integer.parseInt(rs.getString("Id")));
		                newSubscription.setServicename(rs.getString("ServiceName"));
		                newSubscription.setServiceid(rs.getString("ServiceId"));
		                newSubscription.setAction(rs.getString("Action"));
		                newSubscription.setNetwork(rs.getString("Network"));
		                newSubscription.setStatus(rs.getString("Status"));
		                newSubscription.setStatuscode(rs.getString("StatusCode"));
		                newSubscription.setTransactionid(rs.getString("TransactionId"));
		                newSubscription.setMsisdn(rs.getString("Msisdn"));
		                newSubscription.setShortcode(rs.getString("Shortcode"));
		                newSubscription.setCreatedat(rs.getString("CreatedAt"));
		                
		                //send request to ELC Subscription manager
		                TPPHTTPRequest.sendRequest(newSubscription);  
		            }
					
				}catch(Exception e) {
					if(e instanceof SQLException) {
						Connection mycon = null;
						System.out.println(e);
					}else if(e instanceof IOException) {
						System.out.println(e.getMessage());
					}else {
						System.out.println("Unknown Exception Thrown");
					}
					
			 }
	        
	         synchronized(this) {
	            try {
	               this.wait(20000);  // 
	            }
	            catch(InterruptedException ie){}
	         }
	      }
			
	      System.out.println("My Service Finished " + new java.util.Date());
	   }
		
	   /**
	    * Stop this service instance
	    */
	   public void stop() {
	      stopped = true;
	      synchronized(this) {
	         this.notify();
	      }
	   }
}
