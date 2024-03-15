package org.jsp.onetomany.repository;

import org.jsp.onetomany.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
