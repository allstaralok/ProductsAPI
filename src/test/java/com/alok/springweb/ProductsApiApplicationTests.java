package com.alok.springweb;

import static org.junit.Assert.*;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.alok.springweb.entities.Product;

@SpringBootTest
class ProductsApiApplicationTests {

	private static final String PRODUCTS_URL = "http://127.0.0.1:8080/products/";

	@Test
	void testGetProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(PRODUCTS_URL+5, Product.class);
		assertNotNull(product);
		assertEquals("Iphone", product.getName());
	}
	
	@Test
	void testCreateProduct(){
		RestTemplate restTemplate = new RestTemplate();
		Product product2 = new Product();
		product2.setName("Sam Mobile");
		product2.setPrice(300);
		product2.setDescription("Sam Mobile 1x");
		Product productPostRes = restTemplate.postForObject(PRODUCTS_URL,product2 ,Product.class);
		
		assertNotNull(productPostRes);
		assertNotNull(productPostRes.getId());
		assertEquals("Sam Mobile", productPostRes.getName());
	}
	
	@Test
	void testUpdateProduct()
	{
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(PRODUCTS_URL+2, Product.class);
		product.setPrice(2000);
		restTemplate.put(PRODUCTS_URL, product);
		
		product = restTemplate.getForObject(PRODUCTS_URL+2, Product.class);
		assertEquals(2000, product.getPrice());
	}
	
	@Test
	void deleteProduct() {
		RestTemplate restTemplate = new RestTemplate();
		//Product product = restTemplate.getForObject(PRODUCTS_URL+5, Product.class);
		restTemplate.delete(PRODUCTS_URL+5);
		
		Product product = restTemplate.getForObject(PRODUCTS_URL+5, Product.class);
		assertNull(product);
	}

}
