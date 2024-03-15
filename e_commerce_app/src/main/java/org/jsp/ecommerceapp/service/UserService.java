package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.exceptions.InvalidCredentials;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ECommerceApplicationEmailService emailService;

	public ResponseEntity<ResponseStructure<User>> save(HttpServletRequest request, User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(40));
		structure.setBody(userDao.saveUser(user));
		String message = emailService.welcomeEmail(request, user);
		structure.setMessage("User has been saved, " + message);
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
		structure.setMessage("User has been saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(HttpStatus.CREATED);
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
		throw new IdNotFound("Invlid Id");
	}

	public ResponseEntity<ResponseStructure<User>> deleteById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();

		if (userDao.deleteById(id)) {
			structure.setMessage("User has been deleted!!");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid Id");

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
		User u = userDao.verify(email, password);
		if (u != null) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setBody(u);
			structure.setMessage("User has been verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentials("Invalid email or password");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		User u = userDao.verify(phone, password);
		if (u != null) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setBody(u);
			structure.setMessage("User has been verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentials("Invalid Phone number or password");
	}

	public ResponseEntity<ResponseStructure<String>> activateUser(String token) {
		Optional<User> u = userDao.findByToken(token);
		if (u.isPresent()) {
			User user = u.get();
			user.setStatus(AccountStatus.ACTIVE.toString());
			user.setToken(null);
			userDao.saveUser(user);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setBody("User Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFound("Invalid Token");
	}

}
