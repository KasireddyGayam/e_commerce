package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public void deleteByid(int id) {
		repository.deleteById(id);
	}

	public Optional<Product> findById(int id) {
		return repository.findById(id);
	}

	public List<Product> findByBrand(String brand) {
		return repository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return repository.findByCategory(category);
	}

	public List<Product> findByMerchantById(int merchant_id) {
		return repository.findByMerchantId(merchant_id);
	}

}
