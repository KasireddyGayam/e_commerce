package org.jsp.ecommerceapp.repository;

import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select user from User user where user.email=?1 and user.password=?2")
	public Optional<User> verify(String email, String password);

	@Query("select u from User u where u.phone=?1 and u.password=?2")
	public Optional<User> verify(long phone, String password);

	public Optional<User> findByToken(String token);
	

}
