package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@CrossOrigin
public class AddressController {
	@Autowired
	private AddressService service;

	@PostMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<Address>> save(@RequestBody Address address, @PathVariable int user_id) {
		return service.save(address, user_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> update(@RequestBody Address address) {
		return service.update(address);
	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		return service.findById(id);
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findByUser(@PathVariable int user_id) {
		return service.findByUser(user_id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Address>>> findAll()
	{
		return service.findAll();
	}
}
