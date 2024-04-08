package org.jsp.ecommerceapp.repository;

import java.util.List;

import org.jsp.ecommerceapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	@Query("select a from Address a where a.user.id=?1")
	public List<Address> findByUserid(int user_id);

	public List<Address> findByLandmark(String landmark);

	public List<Address> findByPincode(int pincode);

}
