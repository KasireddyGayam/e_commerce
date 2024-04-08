package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dao.ProductDao;
import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dao.UserOrderDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFound;
import org.jsp.ecommerceapp.exceptions.ProductNotFoundExceotion;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserOrderDao orderDo;

	public ResponseEntity<ResponseStructure<Product>> save(Product product, int merchant_id) {
		Optional<Merchant> m = merchantDao.findById(merchant_id);
		if (m.isPresent()) {
			Merchant merchant = m.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setBody(productDao.saveProduct(product));
			structure.setMessage("Product has been saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFound("Invalid merchant id");
	}

	public ResponseEntity<ResponseStructure<Product>> update(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> p = productDao.findById(product.getId());
		if (p.isPresent()) {
			Product p1 = p.get();
			p1.setCost(product.getCost());
			p1.setName(product.getName());
			p1.setBrand(product.getBrand());
			p1.setCategory(product.getCategory());
			p1.setDescription(product.getDescription());
			p1.setImage_url(product.getImage_url());
			structure.setBody(productDao.saveProduct(p1));
			structure.setMessage("Product has been updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> p = productDao.findById(id);
		if (p.isPresent()) {
			structure.setBody(p.get());
			structure.setMessage("Product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid product id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		List<Product> products = productDao.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundExceotion("Products not found");
		}
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setBody(products);
		structure.setMessage("List of Products found ");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		List<Product> products = productDao.findByBrand(brand);
		if (products.isEmpty()) {
			throw new ProductNotFoundExceotion("Products not found with entered Brand");
		}
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setBody(products);
		structure.setMessage("List of Products found ");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		List<Product> products = productDao.findByCategory(category);
		if (products.isEmpty()) {
			throw new ProductNotFoundExceotion("Products not found with entered Category");
		}
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setBody(products);
		structure.setMessage("List of Products found ");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id) {
		List<Product> products = productDao.findByMerchantById(merchant_id);
		if (products.isEmpty()) {
			throw new ProductNotFoundExceotion("Products not found with enetered Merchant Id");
		}
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setBody(products);
		structure.setMessage("List of Products found ");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		Optional<Product> p = productDao.findById(id);
		if (p.isPresent()) {
			productDao.delete(p.get().getId());
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setBody("Product deleted");
			structure.setMessage("Product foundand deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFound("Invalid product id");
	}
	
	public ResponseEntity<ResponseStructure<String>> addToCart(int user_id,int product_id)
	{
		Optional<Product> p=productDao.findById(product_id);
		Optional<User> u=userDao.findById(user_id);
		if(u.isEmpty()||p.isEmpty())
			throw new IllegalArgumentException("Invalid user id or product id");
		ResponseStructure<String> structure=new ResponseStructure<>();
		u.get().getCart().add(p.get());
		userDao.saveUser(u.get());
		structure.setBody("User and Product found");
		structure.setMessage("Product added to cart");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<String>> addToWishList(int user_id,int product_id)
	{
		Optional<Product> p=productDao.findById(product_id);
		Optional<User> u=userDao.findById(user_id);
		if(u.isEmpty()||p.isEmpty())
			throw new IllegalArgumentException("Invalid user id or product id");
		ResponseStructure<String> structure=new ResponseStructure<>();
		u.get().getWishList().add(p.get());
		userDao.saveUser(u.get());
		structure.setBody("User and Product found");
		structure.setMessage("Product added to WishList");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<String>> placeOrder(int user_id,int product_id)
	{
		Optional<Product> p=productDao.findById(product_id);
		Optional<User> u=userDao.findById(user_id);
		if(u.isEmpty()||p.isEmpty())
			throw new IllegalArgumentException("Invalid user id or product id");
		UserOrder order=new UserOrder();
		ResponseStructure<String> structure=new ResponseStructure<>();
		u.get().getOrders().add(order);
		userDao.saveUser(u.get());
		p.get().setOrder(order);
		order.getProducts().add(p.get());
		order.setUser(u.get());
		orderDo.saveOrder(order);
		
//		productDao.saveProduct(p.get());
		structure.setBody("User and Product found");
		structure.setMessage("Order  has been placed");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
	}
	
}
