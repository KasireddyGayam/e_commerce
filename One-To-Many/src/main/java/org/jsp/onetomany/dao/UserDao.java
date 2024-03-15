package org.jsp.onetomany.dao;

import java.util.Optional;

import org.jsp.onetomany.model.User;
import org.jsp.onetomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteById(int id) {
		repository.deleteById(id);
		return true;
	}

}
