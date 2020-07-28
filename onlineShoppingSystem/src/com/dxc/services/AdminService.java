package com.dxc.services;


import java.util.List;

import com.dxc.daos.AdminDaoImpl;
import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class AdminService implements IAdminService
{
 AdminDaoImpl dao=new AdminDaoImpl();

public boolean loginAdmin(Admin a) 
{
return dao.loginAdmin(a);
}

public void addAdmin(Admin a) 
{
	dao.addAdmin(a);	
}

public void addproduct(Product p) 
{
   dao.addproduct(p);	
}

public List<Product> getAllProducts() {
	
	return dao.getAllProducts();
}

public void addcustomer(Customer c) 
{
	dao.addcustomer(c);	
}
public boolean findcustomer(int customerid) {
	
	return dao.findcustomer(customerid);
}

public void removecustomer(int i) 
{
	dao.removecustomer(i);	
}

public List<Customer> getAllCustomers() 
{
 return dao.getAllCustomers();
}

}
