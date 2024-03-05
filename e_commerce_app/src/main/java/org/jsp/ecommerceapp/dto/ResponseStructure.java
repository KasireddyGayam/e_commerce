package org.jsp.ecommerceapp.dto;

public class ResponseStructure<T> {
	private String message;
	private T Body;
	private int statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return Body;
	}

	public void setBody(T body) {
		Body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
