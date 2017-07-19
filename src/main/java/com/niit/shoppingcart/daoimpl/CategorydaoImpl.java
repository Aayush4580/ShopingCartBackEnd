package com.niit.shoppingcart.daoimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@SuppressWarnings("deprecation")
@Repository("categoryDAO")
@Transactional 

public class CategorydaoImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public CategorydaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory= sessionFactory;
	}
	
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Category> list() {
		/*System.out.println(":::: my query output ::::::::::::::::::::"+getCurrentSession().createQuery("from Category").list());*/
		
		/*List<Category> clist = getCurrentSession().createQuery("from Category").list();
		for(int c = 0 ; c <clist.size() ; c++ ){
			System.out.println(c);
			Category cat = clist.get(c);
			System.out.println("id ::: "+cat.getId());
			
		}*/
		return getCurrentSession().createQuery("from Category").list();
	}

	public boolean save(Category category) {
		try{
			getCurrentSession().save(category);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}

	public boolean update(Category category) {
		try{
			getCurrentSession().update(category);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}

	public boolean delete(String id) {
		try{
			getCurrentSession().delete(getCategoryByID(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}


	@SuppressWarnings("rawtypes")
	public Category getCategoryByNmane(String name) {
		/*	return  (Category) getCurrentSession().createQuery("from Category where name = ?")
		.setString(0, name).uniqueResult();
		*/
		 Query query=getCurrentSession().createQuery("from Category where name=?");
		 query.setString(0, name);
		return (Category) query.uniqueResult();
	}
	public Category getCategoryByID(String id) {
		// TODO Auto-generated method stub
		return (Category)getCurrentSession().get(Category.class,id);
	}



}
