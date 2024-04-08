package org.jsp.ecommerceapp.dao;

import java.util.Optional;

import org.jsp.ecommerceapp.model.UserOrder;
import org.jsp.ecommerceapp.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserOrderDao
{
	@Autowired
	private UserOrderRepository repository;
	
	public UserOrder saveOrder(UserOrder order)
	{
		return repository.save(order);
	}
	
	public Optional<UserOrder> findById(int id)
	{
		return repository.findById(id);
	}

}
