package org.jsp.ecommerceapp.exceptions;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
@RestControllerAdvice
public class ECommerceExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> handleINF(IdNotFound e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Cannot found");
		structure.setBody(e.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentials.class)
	public ResponseEntity<ResponseStructure<String>> handleIcF(InvalidCredentials e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Cannot found");
		structure.setBody(e.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundExceotion.class)
	public ResponseEntity<ResponseStructure<String>> handlePNFE(ProductNotFoundExceotion e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Products not found");
		structure.setMessage(e.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleANFE(AddressNotFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Address not found");
		structure.setMessage(e.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ResponseStructure<String>> handleISE(IllegalStateException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Account should be activated to signin");
		structure.setMessage(e.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

}
