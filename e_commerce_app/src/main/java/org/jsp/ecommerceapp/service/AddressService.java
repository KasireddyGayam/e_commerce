package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.AddressDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> save(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		structure.setBody(addressDao.saveAddress(address));
		structure.setMessage("Address has been saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> update(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> a = addressDao.findById(address.getId());
		if (a.isPresent()) {
			Address a1 = a.get();
			a1.setCity(address.getCity());
			a1.setHouse_no(address.getHouse_no());
			a1.setLandmark(address.getLandmark());
			a1.setMandal(address.getMandal());
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

}
