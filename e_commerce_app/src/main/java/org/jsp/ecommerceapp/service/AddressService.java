package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.AddressDao;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.AddressNotFoundException;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> save(Address address, int user_id) {
		Optional<User> u = userDao.findById(user_id);
		if (u.isPresent()) {
			User user = u.get();
			user.getAddresses().add(address);
			address.setUser(user);
			ResponseStructure<Address> structure = new ResponseStructure<>();
			structure.setBody(addressDao.saveAddress(address));
			structure.setMessage("Address has been saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFound("Invalid User id");
	}

	public ResponseEntity<ResponseStructure<Address>> update(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> a = addressDao.findById(address.getId());
		if (a.isPresent()) {
			Address a1 = a.get();
			a1.setCity(address.getCity());
			a1.setAddressType(address.getAddressType());
			a1.setHouse_number(address.getHouse_number());
			a1.setBuildingName(address.getBuildingName());
			a1.setLandmark(address.getLandmark());
			a1.setCountry(address.getCountry());
			a1.setPincode(address.getPincode());
			a1.setState(address.getState());
			structure.setBody(addressDao.saveAddress(a1));
			structure.setMessage("Address has been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFound("Invalid adress id");
	}

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		Optional<Address> a = addressDao.findById(id);
		if (a.isPresent()) {
			ResponseStructure<Address> structure = new ResponseStructure<>();
			structure.setBody(a.get());
			structure.setMessage("Address found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid Address Id");
	}

	public ResponseEntity<ResponseStructure<List<Address>>> findAll() {
		List<Address> l = addressDao.findAll();
		if (l.isEmpty()) {
			throw new AddressNotFoundException("Address is not found");
		}
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		structure.setBody(l);
		structure.setMessage("Address Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Address>>> findByUser(int user_id) {
		List<Address> l = addressDao.findByUserId(user_id);
		if (l.isEmpty()) {
			throw new AddressNotFoundException("Address not found with enetered user id");
		}
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		structure.setBody(l);
		structure.setMessage("Address found");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.FOUND);
	}
	
}
