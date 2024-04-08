package org.jsp.onetomany.exception;

import org.jsp.onetomany.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserFoodExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleINF(IdNotFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody(e.getMessage());
		structure.setMessage("Invalid Id ");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody(e.getMessage());
		structure.setMessage("Invalid Credentials");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
