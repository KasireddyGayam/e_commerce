package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.UserOrder;
import org.jsp.ecommerceapp.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserOrderDao {
	@Autowired
	private UserOrderRepository repository;

	public UserOrder saveOrder(UserOrder order) {
		return repository.save(order);
	}

	public Optional<UserOrder> findById(long l) {
		return repository.findById(l);
	}

	public List<UserOrder> findByUserId(int user_id) {
		return repository.findByUserId(user_id);
	}

}
