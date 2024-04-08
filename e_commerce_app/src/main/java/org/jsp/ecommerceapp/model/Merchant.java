package org.jsp.ecommerceapp.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String gst_number;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false, unique = true)
	private String email;
	private String status;
	@Column(nullable = false)
	private String password;
	private String token;
	@OneToMany(mappedBy = "merchant")
	private List<Product> products;

}
