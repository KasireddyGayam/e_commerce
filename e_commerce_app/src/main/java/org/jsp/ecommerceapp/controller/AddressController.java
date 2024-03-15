package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	private AddressService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> save(@RequestBody Address address) {
		return service.save(address);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> update(@RequestBody Address address) {
		return service.update(address);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		return service.findById(id);
	}
}
