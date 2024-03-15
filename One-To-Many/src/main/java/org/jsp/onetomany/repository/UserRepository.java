package org.jsp.onetomany.repository;

import org.jsp.onetomany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
