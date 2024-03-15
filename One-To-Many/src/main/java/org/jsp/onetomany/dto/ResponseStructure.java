package org.jsp.onetomany.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private T Body;
	private int statusCode;

}
