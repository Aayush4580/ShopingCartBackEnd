package com.niit.shoppingcart.daoimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
/*import org.hibernate.Session;*/
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;
@SuppressWarnings("deprecation")
@Repository("productDAO")
@Transactional 
@Component
public class ProductdaoImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public ProductdaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	/*private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}*/
	@SuppressWarnings("unchecked")
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean save(Product product) {
		try{
			sessionFactory.getCurrentSession().save(product);
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
			sessionFactory.getCurrentSession().update(product);
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
			sessionFactory.getCurrentSession().delete(getProductByID(id));
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
		return (Product)sessionFactory.getCurrentSession().get(Product.class,id);
	}

	@SuppressWarnings("rawtypes")
	public Product getProductByNmane(String name) {
		 Query query=sessionFactory.getCurrentSession().createQuery("from Product where name=?");
		 query.setString(0, name);
		return (Product) query.uniqueResult();
	}


}
