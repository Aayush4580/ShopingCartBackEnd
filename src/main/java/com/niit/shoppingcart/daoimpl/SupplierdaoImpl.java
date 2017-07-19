package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;
@SuppressWarnings("deprecation")
@Repository("supplierDAO")
@Transactional
public class SupplierdaoImpl implements SupplierDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public SupplierdaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory= sessionFactory;
	}
	
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> list() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Supplier").list();
	}

	public boolean save(Supplier supplier) {try{
		getCurrentSession().save(supplier);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;	
		
	}
	return true;
	}

	public boolean update(Supplier supplier) {try{
		getCurrentSession().update(supplier);
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
			getCurrentSession().delete(getSupplierByID(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
			
		}
		return true;
	
	}

	public Supplier getSupplierByID(String id) {
		// TODO Auto-generated method stub
		return (Supplier)getCurrentSession().get(Supplier.class,id);
	
	}

	@SuppressWarnings("rawtypes")
	public Supplier getSupplierByNmane(String name) {

		/*	return  (Category) getCurrentSession().createQuery("from Category where name = ?")
		.setString(0, name).uniqueResult();
		*/
		 Query query=getCurrentSession().createQuery("from Supplier where name=?");
		 query.setString(0, name);
		return (Supplier) query.uniqueResult();
	
	}

}
