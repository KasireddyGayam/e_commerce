package org.jsp.ecommerceapp.repository;

import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	public Merchant verify(@RequestParam String email, @RequestParam String password);

	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	public Merchant verify(@RequestParam long phone, @RequestParam String password);

}
