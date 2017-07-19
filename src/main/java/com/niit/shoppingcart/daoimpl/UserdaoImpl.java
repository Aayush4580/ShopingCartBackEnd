package com.niit.shoppingcart.daoimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

@SuppressWarnings("deprecation")
@Repository("userDAO")
@Transactional
@Component
public class UserdaoImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	//write user defined constructor with one parameter i.e., sessionFactory
	
	public UserdaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	/*this save method create a record in the user table
	if the record is created,it will return true
	else returns false*/
	public boolean save(User user) {
		try{
		sessionFactory.getCurrentSession().save(user);
		//Transaction tx=sessionFactory.getCurrentSession().getTransaction();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		/*finally{
			sessionFactory.getCurrentSession().close();
		}*/
		return true;
	}
	/*this update method create a record in the user table
	if the record is created,it will return true
	else returns false*/

	public boolean update(User user) {
		// TODO Auto-generated method stub
		try{
		sessionFactory.getCurrentSession().update(user);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean validate(String id, String password) {
	@SuppressWarnings("rawtypes")
	Query query=	sessionFactory.getCurrentSession().createQuery("from User where id= ? and password= ?");
	query.setString(0,id);//index will start from 0,will get once exception
	query.setString(1,password);
	//strike out means the method is deprecated;future version it'll get removed
	//solution:use @SuppressWarning
	//in the User table with this id and password there will one or zero records will exist
	//i.e.,uniqueresult
	
	if(query.uniqueResult()==null)
	{
		//means no row exist,invalid credentials
		return false;
	}
	else
	{
		//row exist
		return true;
	}
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	
	public User get(String id)
	{
		//get method fetch data from user table based on primary key i.e.,id
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
}
