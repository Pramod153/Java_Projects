package com.learning.EmployeeManagement.Payload;

public class APIResponse {

	private String msg;
	private boolean status;
	
	public APIResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIResponse(String msg, boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
