package org.jsp.ecommerceapp.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String order_name;
	private String type;
	private double cost;
	@CreationTimestamp
	private LocalDateTime Ordered_time;
	@UpdateTimestamp
	private LocalDateTime del_time;
}
