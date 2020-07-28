package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public interface IAdminService
{
	public boolean loginAdmin(Admin a);
	
	public void addAdmin(Admin a);
	
	public void addproduct(Product p);
	
	public List<Product> getAllProducts();
	
	public void addcustomer(Customer c);
	
	public boolean findcustomer(int customerid);
	
	public void removecustomer(int i);
	
	public List<Customer> getAllCustomers();
	
	}
