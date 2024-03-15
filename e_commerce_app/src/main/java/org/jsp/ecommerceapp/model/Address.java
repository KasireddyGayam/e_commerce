package org.jsp.ecommerceapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String house_no;
	private String landmark;
	private String mandal;
	private String city;
	private String state;
	@Column(nullable = false)
	private int pincode;
	
	
}
