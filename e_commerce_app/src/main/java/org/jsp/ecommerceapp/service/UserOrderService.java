package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.ProductDao;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dao.UserOrderDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserOrderService {
	@Autowired
	private UserOrderDao usDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<UserOrder>> save(int user_id,int product_id)
	{
		Optional<User> u=userDao.findById(user_id);
		Optional<Product> p=productDao.findById(product_id);
		if(u.isEmpty()||p.isEmpty())
			throw new IdNotFound("User or Product is not found");
		UserOrder order=new UserOrder();
		order.getProducts().add(p.get());
		p.get().setOrder(order);
		order.setUser(u.get());
		u.get().getOrders().add(order);
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
