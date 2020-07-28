package com.dxc.services;

import java.util.List;

import com.dxc.daos.*;
import com.dxc.daos.*;
import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class CustomerServiceImpl implements ICustomerService
{
 ICustomerDao dao=new CustomerDaoImpl();
	@Override
	public boolean loginCustomer(Customer c) 
	{
		return dao.loginCustomer(c);
	}
	@Override
	public void AddToCart(int cid, int productno) 
	{
	dao.AddToCart(cid,productno);	
	}
	@Override
	public List<Product> getAllProducts() {
		
		return dao.getAllProducts();
	}
	@Override
	public List<Product> DisplayCartList(int cid) 
	{
		return dao.DisplayCartList(cid);
	}
	@Override
	public boolean paybill(Product p, double finalcost,int cid) 
	{
		return dao.paybill(p,finalcost, cid);
	}
	@Override
	public void addmoneytowallet(int cid, double balance) 
	{
	 dao.addmoneytowallet(cid,balance);	
	}
	@Override
	public double showBalance(int customerid)
	   {
		return dao.showBalance(customerid);	
		}

}
