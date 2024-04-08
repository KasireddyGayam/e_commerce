package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.UserOrder;
import org.jsp.ecommerceapp.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class UserOrderController {
	@Autowired
	private UserOrderService service;

	@PostMapping("/{user_id}/{product_id}")
	public ResponseEntity<ResponseStructure<UserOrder>> save(@PathVariable int user_id, @PathVariable int product_id) {
		return service.save(user_id,product_id);
	}

}
