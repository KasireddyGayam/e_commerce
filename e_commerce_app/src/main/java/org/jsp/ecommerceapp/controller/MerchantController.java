package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(HttpServletRequest request,
			@RequestBody Merchant merchant) {
		return service.save(request, merchant);
	}

	@GetMapping("/activate-merchant")
	public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token) {
		return service.activateMerchant(token);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return service.update(merchant);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id) {
		return service.findById(id);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Merchant>> deleteAll() {
		return service.deleteAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}

	@PostMapping("/verify-merchant-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.verify(email, password);
	}

	@PostMapping("/verify-merchant-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return service.verify(phone, password);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Merchant>>> find() {
		return service.findAll();
	}
}
