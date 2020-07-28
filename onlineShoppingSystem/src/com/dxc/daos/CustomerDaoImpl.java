package com.dxc.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class CustomerDaoImpl implements ICustomerDao
{
private static SessionFactory sessionFactory;
	
	static{
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
	}

	@Override
	public boolean loginCustomer(Customer c) 
	{
		 Session session=sessionFactory.openSession();
	       Query query=session.createQuery("from Customer");
	       List<Customer> list=query.getResultList();
	       for(Customer l : list)
	       {
	    	   
	    	   if(c.getCustomerid() == (l.getCustomerid()) && c.getPassword().equals(l.getPassword()))
	    	   {
	    		   return true;
	    	   }
	       }
	       return false;
		
	}

	@Override
	public void AddToCart(int cid, int productno) 
	{
		Cart c=new Cart();
		c.setCustomerid(cid);
		c.setProductno(productno);
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(c);
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
	public List<Product> DisplayCartList(int cid) 
	{
		List<Product> list=new ArrayList<Product>();
		
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Cart");
		List<Cart> clist=query.getResultList();
		for(Cart cart:clist)
		{
		  if(cid==cart.getCustomerid())	
		  {
			  int productno=cart.getProductno();
			  Query query1=session.createQuery("from Product where productno=:pid");
			  query1.setParameter("pid", productno);
			  List<Product> list3=query1.getResultList();
			  for(Product product : list3)
			  {
				  list.add(product);
			  }
		  }
		}
		return list;
	}

	@Override
	public boolean paybill(Product p, double finalcost,int cid) 
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Customer");
		List<Customer> clist=query.getResultList();
		for(Customer customer:clist)
		{
		  if(cid==customer.getCustomerid())	
		  {
			  double balance=customer.getBalance();
			  if(balance<finalcost)
			  {
				  return false;
			  }
			  balance=balance-finalcost;
			  Query query1=session.createQuery("Update Customer set balance=:balance where customerid=:customerid");
			   query1.setParameter("balance", balance);
			   query1.setParameter("customerid", cid);
			   query1.executeUpdate();
		  }
		} 
		
		Query query2=session.createQuery("from Product");
		List<Product> plist=query2.getResultList();
		for(Product product:plist)
		{
		  if(p.getProductno()==product.getProductno())	
		  {
			  double quant=product.getQuantity();
			  
			  quant=quant-p.getQuantity();
			  Query query1=session.createQuery("Update Product set quantity=:quant where productno=:productno");
			   query1.setParameter("quant", quant);
			   query1.setParameter("productno", product.getProductno());
			   query1.executeUpdate();
		  }
		}  	
		 Query query1=session.createQuery("delete from Cart where customerid=:cid AND productno=:productno");
		   query1.setParameter("cid", cid);
		   query1.setParameter("productno", p.getProductno());
		   query1.executeUpdate();
		   session.getTransaction().commit();
		
		return true;
	}

	@Override
	public void addmoneytowallet(int cid, double balance) 
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Customer");
		List<Customer> clist=query.getResultList();
		for(Customer customer:clist)
		{
		  if(cid==customer.getCustomerid())	
		  {
			  double amount=customer.getBalance();
			 
			  balance=balance+amount;
			  Query query1=session.createQuery("Update Customer set balance=:balance where customerid=:customerid");
			   query1.setParameter("balance", balance);
			   query1.setParameter("customerid", cid);
			   query1.executeUpdate();
		  }  	
	}
}

	@Override
	public double showBalance(int customerid) {
		double amount=0.0;
		Customer c=null;
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Customer where customerid=:customerid");
		query.setParameter("customerid", customerid);
		List<Customer> clist=query.getResultList();
		c=clist.get(0) ;
		amount=c.getBalance();
		session.close();
	
		return amount;
		}
	
}
