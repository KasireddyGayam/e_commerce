package org.jsp.ecommerceapp.dao;

import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}

	public Optional<User> verify(String email, String password) {
		return repository.verify(email, password);
	}

	public Optional<User> verify(long phone, String password) {
		return repository.verify(phone, password);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public boolean deleteById(int id) {
		repository.deleteById(id);
		return true;
	}
	
	public Optional<User> findByToken(String token)
	{
		return repository.findByToken(token);
	}

}
