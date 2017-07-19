package com.niit.shoppingcart.daoimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
/*import org.hibernate.Session;*/
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;
@SuppressWarnings("deprecation")
@Repository("productDAO")
@Transactional 
public class ProductdaoImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductdaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean save(Product product) {
		try{
			getCurrentSession().save(product);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}

	public boolean update(Product product) {
		try{
			getCurrentSession().update(product);
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
			getCurrentSession().delete(getProductByID(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	}

	public Product getProductByID(String id) {
		// TODO Auto-generated method stub
		return (Product)getCurrentSession().get(Product.class,id);
	}

	@SuppressWarnings("rawtypes")
	public Product getProductByNmane(String name) {
		 Query query=getCurrentSession().createQuery("from Product where name=?");
		 query.setString(0, name);
		return (Product) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductsByCategoryID(String categoryID) {
		
		@SuppressWarnings("rawtypes")
		Query query=	getCurrentSession().createQuery("from Product where categoryID=?");
		query.setString(0, categoryID);
		
	    return	query.list();
	}
	public Product get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts()
	{
		return getCurrentSession().createQuery("from Product").list();
	}

}
