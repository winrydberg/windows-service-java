package com.elcuto.service;

public class RequestData {
	public int id;
	public String network;
	public String msisdn;
	public String transactionid;
	public String shortcode;
	public String servicename;
	public String status;
	public String statuscode;
	public String action;
	public String createdat;
	public String serviceid;
	
	
	public RequestData() {

	}
	
//	public RequestData(String transactionid, String msisdn, String network, String shortcode, String servicename, String status, String statuscode) {
//		this.msisdn = msisdn;
//		this.transactionid = transactionid;
//		this.network = network;
//		this.shortcode = shortcode;
//		this.servicename = servicename;
//		this.status = status;
//		this.statuscode = statuscode;
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getNetwork() {
		return network;
	}



	public void setNetwork(String network) {
		this.network = network;
	}



	public String getMsisdn() {
		return msisdn;
	}



	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}



	public String getTransactionid() {
		return transactionid;
	}



	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}



	public String getShortcode() {
		return shortcode;
	}



	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}



	public String getServicename() {
		return servicename;
	}



	public void setServicename(String servicename) {
		this.servicename = servicename;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getStatuscode() {
		return statuscode;
	}



	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

}
