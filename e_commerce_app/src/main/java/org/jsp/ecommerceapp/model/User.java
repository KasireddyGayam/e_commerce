package org.jsp.ecommerceapp.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private String status;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	private String token;
	

}
