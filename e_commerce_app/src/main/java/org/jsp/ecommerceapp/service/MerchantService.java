package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Merchant>> save(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setBody(merchantDao.savMerchant(merchant));
		structure.setMessage("Merchnat has been saved");
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
		structure.setBody(null);
		structure.setMessage("Invalid id");
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.BAD_REQUEST);

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
		return new ResponseEntity<ResponseStructure<Merchant>>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Merchant>> deleteById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		try {
			merchantDao.deleteById(id);
			structure.setMessage("Merchant deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			structure.setBody(null);
			structure.setMessage("Merchant not found");
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		Merchant m = merchantDao.verifyMerchant(email, password);
		if (m != null) {
			structure.setBody(m);
			structure.setMessage("Merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("Inavlid Email or password");
		structure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> verify(long phone, String password) {

		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Merchant m = merchantDao.verifyMerchant(phone, password);
		if (m != null) {
			structure.setBody(m);
			structure.setMessage("Merchant verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		structure.setBody(null);
		structure.setMessage("Inavlid phone number or password");
		structure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
//		List<Merchant> l = merchantDao.findAll();
		structure.setBody(merchantDao.findAll());
		structure.setMessage("Fetched All merchants");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
	}

}
