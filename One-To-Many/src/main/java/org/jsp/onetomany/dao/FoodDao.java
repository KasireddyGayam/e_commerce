package org.jsp.onetomany.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.onetomany.model.Food;
import org.jsp.onetomany.model.User;
import org.jsp.onetomany.repository.FoodRepository;
import org.jsp.onetomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDao {
	@Autowired
	private FoodRepository repository;

	public Food save(Food order) {
		return repository.save(order);
	}
	

	public Optional<Food> findById(int id) {
		return repository.findById(id);
	}

}
