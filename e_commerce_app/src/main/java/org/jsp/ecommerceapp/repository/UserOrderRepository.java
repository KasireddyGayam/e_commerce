package org.jsp.ecommerceapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
	@Query("select u from UserOrder u where u.user.id=?1")
	List<UserOrder> findByUserId(int user_id);
	
	
	Optional<UserOrder> findById(long id);

}
