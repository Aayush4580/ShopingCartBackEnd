package com.niit.shoppingcart.daoimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
/*import org.hibernate.Session;*/
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;
@SuppressWarnings("deprecation")
@Repository("categoryDAO")
@Transactional 
@Component
public class CategorydaoImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public CategorydaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	/*private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}*/
	@SuppressWarnings("unchecked")
	public List<Category> list() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	public boolean save(Category category) {
		try{
			sessionFactory.getCurrentSession().save(category);
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
			sessionFactory.getCurrentSession().update(category);
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
			sessionFactory.getCurrentSession().delete(getCategoryByID(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}

	public Category getCategoryByID(String id) {
		// TODO Auto-generated method stub
		return (Category)sessionFactory.getCurrentSession().get(Category.class,id);
	}

	@SuppressWarnings("rawtypes")
	public Category getCategoryByNmane(String name) {
		 Query query=sessionFactory.getCurrentSession().createQuery("from Category where name=?");
		 query.setString(0, name);
		return (Category) query.uniqueResult();
	}


}
