package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.User;

public interface UserDAO {
	//declare methods
	
	/*what type of operations we are going to do for user.
	operation
	1.create/register-save
	2.update the user details-update
	3.validate the credentials -validate
	4.get all users-list
	
	declare methods with proper signature
	acces_specifier|return_type|method_name(parameter_list)|throws exception_list*/
	
	//create user-save
	public boolean save(User user);
	
	//update the user details-update
	public boolean update(User user);

	//validate the credentials-validate
	public boolean validate(String id,String password);

	//get all the users-list
	public List<User> list();	
	
	//get user details based on userID
	public User get(String id);
	
}
