package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.MyCartDAO;
import com.niit.shoppingcart.domain.MyCart;

@SuppressWarnings("deprecation")
@Repository("myCartDAO")
@Transactional
@Component
public class MyCartdaoImpl implements MyCartDAO {
	private static Logger log = LoggerFactory.getLogger(MyCartdaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	
	public MyCartdaoImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public List<MyCart> list(String userID) {
		log.debug("Starting of the method list");
		String hql = "from MyCart where userID=" + "'" + userID + "'  and status = " + "'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method list");
		return query.list();

	}

	
	public boolean save(MyCart myCart) {
		log.debug("Starting of the method save");

		myCart.setId(getMaxId());
		try {
			sessionFactory.getCurrentSession().save(myCart);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public MyCart getCart(String userID, String productName)
	{
		String hql = "from MyCart where userID = ? and productName = ?";
		Query  query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, userID);
		query.setString(1, productName);
		return (MyCart) query.uniqueResult();
		
	}
	
	public Integer getQuantity(String userID, String productName)
	{
		String hql = "select quntity from  MyCart where userID = ?  &&  productName =?";
	
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, userID);
		query.setString(1, productName);
		log.debug("Ending of the method getTotalAmount");
		return (Integer) query.uniqueResult();
	}


	public Long getTotalAmount(String userID) {
		log.debug("Starting of the method getTotalAmount");
		String hql = "select sum(price*quantity) from MyCart where userID=" + "'" + userID + "' " + "  and status = " + "'N'";
		log.debug("hql" + hql);

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method getTotalAmount");
		return (Long) query.uniqueResult();

	}

	private Long getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		Long maxID = 100L;
		try {
			String hql = "select max(id) from MyCart";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxID = (Long) query.uniqueResult();
		} catch (HibernateException e) {
			log.debug("It seems this is first record. setting initial id is 100 :");
			maxID = 100L;
			e.printStackTrace();
		}
		log.debug("Max id :" + maxID);
		return maxID + 1;

	}

	
	public MyCart get(String id) {
		return (MyCart) sessionFactory.getCurrentSession().get(MyCart.class, id);
	}

	
	public boolean delete(MyCart myCart) {
		myCart.setStatus('X');
		
		return update(myCart);
	}

	
	public boolean update(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().update(myCart);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void insert(MyCart myCart) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(myCart);
	}
	
	
}








