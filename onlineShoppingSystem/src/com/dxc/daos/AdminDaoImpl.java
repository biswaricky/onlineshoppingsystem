package com.dxc.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;


public class AdminDaoImpl implements IAdminDao
{
private static SessionFactory sessionFactory;
	
	static{
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
	}
	
	public void addAdmin(Admin a)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(a);
		session.getTransaction().commit();
	}
	
	public boolean loginAdmin(Admin a) 
	{
       Session session=sessionFactory.openSession();
       Query query=session.createQuery("from Admin");
       List<Admin> list=query.getResultList();
       for(Admin l : list)
       {
    	   
    	   if(a.getAdminid().equals(l.getAdminid()) && a.getPassword().equals(l.getPassword()))
    	   {
    		   return true;
    	   }
       }
       return false;
	}

	@Override
	public void addproduct(Product p) 
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
	}

	@Override
	public List<Product> getAllProducts() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		return query.getResultList();
		
	}

	@Override
	public void addcustomer(Customer c) 
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();	
	}
      
	
	public boolean findcustomer(int customerid) 
	{
		Customer c=null;
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Customer where customerid=:customerid");
		query.setParameter("customerid", customerid);
		List<Customer> c1=query.getResultList();
		try {
			c=c1.get(0);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	@Override
	public void removecustomer(int i) 
	{
		  Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  Query query=session.createQuery("delete from Customer where customerid=:i");
		  query.setParameter("i",i);
		  query.executeUpdate();
		  session.getTransaction().commit();	
	}

	@Override
	public List<Customer> getAllCustomers() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Customer");
		return query.getResultList();	
		
	}

}
