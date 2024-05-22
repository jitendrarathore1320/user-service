package com.advantal.exceptionHandling;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus message;
//	private String statusMessage;
	private String status;
	private List<String> errorList;
	private Date date;
	private String pathUrl;
	public HttpStatus getMessage() {
		return message;
	}
	public void setMessage(HttpStatus message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPathUrl() {
		return pathUrl;
	}
	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}
	public ApiError(HttpStatus message, String status, List<String> errorList, Date date, String pathUrl) {
		super();
		this.message = message;
		this.status = status;
		this.errorList = errorList;
		this.date = date;
		this.pathUrl = pathUrl;
	}
	public ApiError() {
		super();
	}
	
	
		
}
