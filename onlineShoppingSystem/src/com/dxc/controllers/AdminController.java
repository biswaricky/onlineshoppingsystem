package com.dxc.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.services.AdminService;
import com.dxc.services.IAdminService;


	@Controller
	@RequestMapping("/views")
	public class AdminController 
	{
		
		IAdminService service=new AdminService();
		
		String message =" ";
		
		@RequestMapping("/add")
		public String add(@ModelAttribute Admin a)
		{
			service.addAdmin(a);
			return "message";
		}
		
		@RequestMapping("/login")
		public String login(@ModelAttribute Admin a, Model m )
		{
			boolean b=service.loginAdmin(a);
			if(b)
			{
			return "adminoptions";
			}
			else
			{
				message = "Incorrect Login Credentials";
				m.addAttribute("message",message);
				return "message";
			}
		}
		
		@RequestMapping("/addproduct")
		public String addproduct(@ModelAttribute Product p, Model m)
		{
			service.addproduct(p);
			message="products added";
			m.addAttribute("message", message);
			return "message";
			
		}
		
		@RequestMapping("/display")
		public String getAllProducts(Model m)
		{
			List<Product> list=service.getAllProducts();
			m.addAttribute("list", list);
			return "display";
		}
		
		@RequestMapping("/addcustomer")
		public String addcustomer(@ModelAttribute Customer c, Model m)
		{
			service.addcustomer(c);
			message="Customer added";
			m.addAttribute("message", message);
			return "message";
			
		}
		
		@RequestMapping("/findcustomer")
		public String find(@RequestParam("customerid") int customerid, Model m, HttpSession session)
		{
			boolean status=true;
			
			session.setAttribute("customerid", customerid);
			
			status=service.findcustomer(customerid);
			String msg;
			
			
			
			if(status==true)
			{
				return "removecustomer";
				
			}
			else 
			{
				msg="customer not found !";
				m.addAttribute("msg", msg);
				return "customernotfound";
			}
		}
		
		@RequestMapping("/removecustomer")
		public String removecustomer(@ModelAttribute Customer c, Model m)
		{
			int i=c.getCustomerid();
			service.removecustomer(i);
			message="Customer removed";
			m.addAttribute("message", message);
			return "message";
		}
		
		@RequestMapping("/displaycustomer")
		public String getAllCustomers(Model m)
		{
			List<Customer> list=service.getAllCustomers();
			m.addAttribute("list", list);
			return "displaycustomers";
		}
		@RequestMapping("admin/add")
		public String addAdmin(@ModelAttribute Admin a, Model m) {
			service.addAdmin(a);
			message = "Admin added !";
			m.addAttribute("message", message);
			return "message";
		}
}
