package org.jsp.onetomany.service;

import java.util.Optional;

import org.jsp.onetomany.dao.UserDao;
import org.jsp.onetomany.dto.ResponseStructure;
import org.jsp.onetomany.exception.IdNotFoundException;
import org.jsp.onetomany.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> save(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setBody(userDao.saveUser(user));
		structure.setMessage("User has been saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> update(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> u = userDao.findById(user.getId());
		if (u.isPresent()) {
			User u1 = u.get();
			u1.setName(user.getName());
			u1.setEmail(user.getEmail());
			u1.setPassword(user.getPassword());
			u1.setPassword(user.getPassword());
			structure.setBody(userDao.saveUser(u1));
			structure.setMessage("User Has Been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("Invlid user id");
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> u = userDao.findById(id);
		if (u.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setBody(u.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException("Invalid user id");
	}

	public ResponseEntity<ResponseStructure<User>> deleteById(int id) {
		if (userDao.deleteById(id)) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User has been deleted");
			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new IdNotFoundException("Invalid id");
	}

}
