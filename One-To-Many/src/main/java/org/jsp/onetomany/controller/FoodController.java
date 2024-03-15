package org.jsp.onetomany.controller;

import org.jsp.onetomany.dto.ResponseStructure;
import org.jsp.onetomany.model.Food;
import org.jsp.onetomany.service.FoodService;
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
@RequestMapping("/orders")
public class FoodController {
	@Autowired
	private FoodService service;

	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<Food>> save(@PathVariable int id, @RequestBody Food food) {
		return service.save(id, food);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Food>> findById(@PathVariable int id) {
		return service.findById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Food>> update(@RequestBody Food food) {
		return service.update(food);
	}

}
