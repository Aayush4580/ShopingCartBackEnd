package com.niit.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;

public class ProductDAOTestCase {

	
	@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired  static ProductDAO  productDAO;
	
	@Autowired  static Product product;
	
	
	//The above objects need to initialize
	/**
	 * This method is going execute before calling any one of test case
	 * and will execute only once
	 */
	@BeforeClass
	public static void initialize()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		//get the productDAO from context
		productDAO =  (ProductDAO) context.getBean("productDAO");
		
		//get the product from context
		
		product = (Product)context.getBean("product");
		
	}
	
	@Test
	public void createProductTestCase()
	{
		product.setId("101");
		product.setName("swarup");
		product.setDescription("electrical");
		product.setPrice("10000");
		product.setCategory_id("111");
		product.setSupplier_id("kkkk");
		boolean flag =  productDAO.save(product);
	

		//error - if there is in runtime errors  -  Red mark
		//success  - if expected and actual is same  - green mark
		//fail  - if expected and actual is different  -  blue mark
		assertEquals("createProduct TestCase  abcd",true,flag);
		
	}
	
	
	/*@Test
	public void updateProductTestCase()
	{
		product.setId("1003");
		product.setName("swarup");
		product.setPassword("swarup");
		product.setRole("ROLE_USER");
		product.setContact("8888888");
		boolean flag =  productDAO.update(product);
	

		//error - if there is in runtime errors  -  Red mark
		//success  - if expected and actual is same  - green mark
		//fail  - if expected and actual is different  -  blue mark
		assertEquals(" update product test case",true,flag);
		
	}*/
	
	/*	@Test
	public void validateUSerTestCase()
	{
		
		boolean flag =  productDAO.validate("jayesh", "jayeshhh");
		
		
		assertEquals(true, flag);
		
	}
	
	@Test
	public void getAllProductTestCase()
	{
		int actualSize=   productDAO.list().size();
		
		//will compare actual and expected
		//if actual and expected is same - TC will pass
		//if it is different - TC fail
		assertEquals(12, actualSize);
	}*/
	
	
}











