package org.jsp.ecommerceapp.repository;

import org.jsp.ecommerceapp.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

}
