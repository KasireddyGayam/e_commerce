package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserOrderDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserOrderService {
	@Autowired
	private UserOrderDao usDao;

	public ResponseEntity<ResponseStructure<UserOrder>> save(UserOrder order) {
		ResponseStructure<UserOrder> structure = new ResponseStructure<>();
		structure.setBody(usDao.saveOrder(order));
		structure.setMessage("Order Has been placed");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<UserOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<UserOrder>> update(UserOrder order) {
		Optional<UserOrder> o = usDao.findById(order.getId());
		if (o.isPresent()) {
			ResponseStructure<UserOrder> structure = new ResponseStructure<>();

			UserOrder us = o.get();
			us.setCost(order.getCost());
			us.setOrder_name(order.getOrder_name());
			us.setOrdered_time(order.getOrdered_time());
			us.setType(order.getType());
			structure.setBody(usDao.saveOrder(order));
			structure.setMessage("Order has been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<UserOrder>>(structure, HttpStatus.CREATED);

		}
		throw new IdNotFound("Invalid Order id");
	}

	public ResponseEntity<ResponseStructure<UserOrder>> findById(int id) {
		Optional<UserOrder> o = usDao.findById(id);
		if (o.isPresent()) {
			ResponseStructure<UserOrder> structure = new ResponseStructure<>();
			structure.setBody(o.get());
			structure.setMessage("Order has been found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<UserOrder>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid Order id");
	}
	
}
