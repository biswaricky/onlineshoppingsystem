package com.dxc.daos;

import java.util.List;

import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public interface ICustomerDao 
{
	boolean loginCustomer(Customer c);

	void AddToCart(int cid, int productno);

	List<Product> getAllProducts();

	List<Product> DisplayCartList(int cid);

	boolean paybill(Product p, double finalcost,int cid);

	void addmoneytowallet(int cid, double balance);
	public double showBalance(int customerid);
}
