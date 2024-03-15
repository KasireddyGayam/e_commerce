package org.jsp.onetomany.controller;

import org.jsp.onetomany.dto.ResponseStructure;
import org.jsp.onetomany.model.User;
import org.jsp.onetomany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> save(@RequestBody User user) {
		return service.save(user);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> update(@RequestBody User user) {
		return service.update(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
		return service.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}

}
