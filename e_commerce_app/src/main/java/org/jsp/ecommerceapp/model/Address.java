package org.jsp.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String landmark;
	@Column(nullable = false)
	private String buildingName;
	@Column(nullable = false)
	private String house_number;
	@Column(nullable = false)
	private String addressType;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private int pincode;
	@Column(nullable = false)
	private String country;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

}
