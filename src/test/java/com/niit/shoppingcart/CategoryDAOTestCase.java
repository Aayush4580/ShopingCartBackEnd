package com.niit.shoppingcart;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

public class CategoryDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	// using cause we used Annotation lise @Repository,@Configuration
	@Autowired
	static CategoryDAO categoryDAO;
	@Autowired
	static Category category;

	@BeforeClass
	//it may execute only once before @Test
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
				//get the userDAO from context
				categoryDAO =  (CategoryDAO) context.getBean("categoryDAO");
				
				//get the user from context
				category = (Category)context.getBean("category");
				
	}
	@Test
	public void getAllCategoriesTestCase()
	{
		List<Category> categories=categoryDAO.list();
		assertEquals(5, categories.size());
		
	}
}
