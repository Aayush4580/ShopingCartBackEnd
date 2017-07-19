package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

public class SupplierDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	// using cause we used Annotation lise @Repository,@Configuration
	@Autowired
	static SupplierDAO supplierDAO;
	@Autowired
	static Supplier supplier;

	@BeforeClass
	//it may execute only once before @Test
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
				//get the userDAO from context
				supplierDAO =  (SupplierDAO) context.getBean("supplierDAO");
				
				//get the user from context
				supplier = (Supplier)context.getBean("supplier");
				
	}
	@Test
	public void createUserTestCase()
	{
		supplier.setId("1008");
		supplier.setName("DTDC");
		supplier.setAddress("Hydrabad");
		
		boolean flag =  supplierDAO.save(supplier);
	

		//error - if there is in runtime errors  -  Red mark
		//success  - if expected and actual is same  - green mark
		//fail  - if expected and actual is different  -  blue mark
		assertEquals("createUser TestCase  abcd",true,flag);
		
	}
	
	/*@Test
	public void getAllCategoriesTestCase()
	{
		List<Supplier> suppliers=supplierDAO.list();
		assertEquals(2, suppliers.size());
		
	}*/
}
