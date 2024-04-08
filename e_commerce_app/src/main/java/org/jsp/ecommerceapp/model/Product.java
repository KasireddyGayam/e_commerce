package org.jsp.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name, category, brand, description, image_url;
	@Column(nullable = false)
	private double cost;
	@ManyToOne
	@JoinColumn(name = "merchant_id")
	@JsonIgnore
	private Merchant merchant;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private UserOrder order;

}
