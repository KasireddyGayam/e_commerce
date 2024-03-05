package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		Optional<User> u = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (u.isPresent()) {
			User u1 = u.get();
			u1.setName(user.getName());
			u1.setEmail(user.getEmail());
			u1.setPhone(user.getPhone());
			u1.setPassword(user.getPassword());
			structure.setBody(userDao.updateUser(u1));
			structure.setMessage("User has been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseStructure<User>>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> u = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (u.isPresent()) {
			structure.setBody(u.get());
			structure.setMessage("User found!");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("User not found!!");
		structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ResponseStructure<User>> deleteById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		try {
			userDao.deleteById(id);
			structure.setMessage("User has been deleted!!");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		} catch (Exception e) {
			structure.setMessage("Invalid User Id");
			structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteAll() {
		ResponseStructure<User> structure = new ResponseStructure<>();
		try {
			userDao.deleteAll();
			structure.setMessage("Database has been cleaned");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		} catch (Exception e) {
			structure.setMessage("Internal server error");
			structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User u = userDao.verify(email, password);
		if (u != null) {
			structure.setBody(u);
			structure.setMessage("User has been verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("Invalid email or password");
		structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User u = userDao.verify(phone, password);
		if (u != null) {
			structure.setBody(u);
			structure.setMessage("User has been verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("Invalid phone number or password");
		structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
