package org.jsp.onetomany.service;

import java.util.Optional;

import org.jsp.onetomany.dao.FoodDao;
import org.jsp.onetomany.dao.UserDao;
import org.jsp.onetomany.dto.ResponseStructure;
import org.jsp.onetomany.exception.IdNotFoundException;
import org.jsp.onetomany.model.Food;
import org.jsp.onetomany.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private UserDao uDao;

	public ResponseEntity<ResponseStructure<Food>> save(int id, Food food) {
		Optional<User> u = uDao.findById(id);
		if (u.isPresent()) {
			User u1 = u.get();
			u1.getOrders().add(food);
			food.setUser(u1);
			uDao.saveUser(u1);
			ResponseStructure<Food> structure = new ResponseStructure<>();
			structure.setBody(foodDao.save(food));
			structure.setMessage("Order has been placed");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Food>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("Invalid User id");
	}

	public ResponseEntity<ResponseStructure<Food>> update(Food food) {
		Optional<Food> f = foodDao.findById(food.getId());
		if (f.isPresent()) {
			Food f1 = f.get();
			f1.setCost(food.getCost());
			f1.setLoc(food.getLoc());
			f1.setName(food.getName());
			ResponseStructure<Food> structure = new ResponseStructure<>();
			structure.setBody(foodDao.save(f1));
			structure.setMessage("Order has been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Food>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("Invalid User id or order details");
	}

	public ResponseEntity<ResponseStructure<Food>> findById(int id) {
		Optional<Food> f = foodDao.findById(id);
		if (f.isPresent()) {
			ResponseStructure<Food> structure = new ResponseStructure<>();
			structure.setBody(f.get());
			structure.setMessage("Orders found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Food>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException("Invalid User details");
	}

}
