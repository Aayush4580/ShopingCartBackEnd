package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {

	
	public static void main(String[] args) 
	{
		//this context is bean factory
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		
		context.scan("com.niit");//scan this package and find out where are the annotation @Component
		
		context.refresh();
		
		//if you need any instance,do not create
		//you can ask context to get a particular bean
	//the bean name will same as class name but first charecter will be lower case
		context.getBean("user");
		
		System.out.println("the user instance is created succesfully");
		
		context.getBean("category");
		
		System.out.println("the category instance is created succesfully");
	}
}
