package com.advantal.responsePayload;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ResponsePayload {

	private String message;
	private HttpStatus status;
	private Object data;
	private HttpHeaders headers;
	private Object countUser;
	public ResponsePayload(HttpStatus status,String message,  Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
	public ResponsePayload(HttpStatus status, String message, Object data, Object countUser) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
		this.countUser = countUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public HttpHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
	public Object getCountUser() {
		return countUser;
	}
	public void setCountUser(Object countUser) {
		this.countUser = countUser;
	}
	
	
	
}
