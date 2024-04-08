package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.exceptions.InvalidCredentials;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private ECommerceApplicationEmailService emailService;

	public ResponseEntity<ResponseStructure<Merchant>> save(HttpServletRequest request, Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(20));
		structure.setBody(merchantDao.savMerchant(merchant));
		String message = emailService.welcomeEmail(request, merchant);
		structure.setMessage("Merchant has been saved, "+message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> m = merchantDao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (m.isPresent()) {
			structure.setBody(m.get());
			structure.setMessage("Merchant found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> update(Merchant merchant) {
		Optional<Merchant> m = merchantDao.findById(merchant.getId());
		if (m.isPresent()) {
			Merchant merchant2 = m.get();
			merchant2.setName(merchant.getName());
			merchant2.setEmail(merchant.getEmail());
			merchant2.setPassword(merchant.getPassword());
			merchant2.setPhone(merchant.getPhone());
			merchant2.setGst_number(merchant.getGst_number());
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setBody(merchantDao.updatMerchant(merchant2));
			structure.setMessage("Merchant updated successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseStructure<Merchant>>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		boolean b = merchantDao.deleteById(id);
		if (b) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Merchant deleted");
			structure.setBody("Merchant deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new IdNotFound("Invalid id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> deleteAll() {
		try {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			merchantDao.deleteAll();
			structure.setMessage("Merchant deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<ResponseStructure<Merchant>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ResponseStructure<Merchant>> verify(String email, String password) {

		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> m = merchantDao.verifyMerchant(email, password);
		if (m.isPresent()) {
			Merchant merchant=m.get();
			if(merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is not activated");
			}
			structure.setBody(m.get());
			structure.setMessage("Merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentials("Invalid email or password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verify(long phone, String password) {

		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> m = merchantDao.verifyMerchant(phone, password);
		if (m.isPresent()) {
			Merchant merchant=m.get();
			if(merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is not activated");
			}
			structure.setBody(m.get());
			structure.setMessage("Merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentials("Invalid Phone or password");

	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setBody(merchantDao.findAll());
		structure.setMessage("Fetched All merchants");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> activateMerchant(String token) {
		Optional<Merchant> m = merchantDao.findByToken(token);
		if (m.isPresent()) {
			Merchant merchant = m.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantDao.savMerchant(merchant);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setBody("Merchant Found");
			structure.setMessage("Account verifed and activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFound("Account not verifed");
	}

}
