package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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

	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteAll() {
		return service.deleteAll();
	}

	@GetMapping("/verifyByEmail")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam String email, @RequestParam String password) {
		return service.verifyUser(email, password);
	}

	@GetMapping("/verifyByPhone")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam long phone, @RequestParam String password) {
		return service.verifyUser(phone, password);
	}
}
