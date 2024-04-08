package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository repository;

	public Address saveAddress(Address address) {
		return repository.save(address);
	}

	public Optional<Address> findById(int id) {
		return repository.findById(id);
	}

	public List<Address> findAll() {
		return repository.findAll();
	}

	public List<Address> findByUserId(int user_id) {
		return repository.findByUserid(user_id);
	}

}
