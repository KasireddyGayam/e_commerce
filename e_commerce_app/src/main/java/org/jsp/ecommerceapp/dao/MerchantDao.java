package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {

	@Autowired
	private MerchantRepository repository;

	public Merchant savMerchant(Merchant merchant) {
		return repository.save(merchant);
	}

	public Merchant updatMerchant(Merchant merchant) {
		return repository.saveAndFlush(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteById(int id) {
		Optional<Merchant> m = findById(id);
		if (m.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public Merchant verifyMerchant(String email, String password) {
		return repository.verify(email, password);
	}

	public Merchant verifyMerchant(long phone, String password) {
		return repository.verify(phone, password);
	}
	
	public List<Merchant> findAll()
	{
		return repository.findAll();
	}
}
