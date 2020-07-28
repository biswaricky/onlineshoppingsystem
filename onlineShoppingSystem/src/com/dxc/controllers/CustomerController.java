package com.dxc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.pojos.Cart;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.services.CustomerServiceImpl;
import com.dxc.services.ICustomerService;

	@Controller
	@RequestMapping("/views")
	public class CustomerController 
	{
		
		ICustomerService service=new CustomerServiceImpl();
		
		String message =" ";
		
		int cid;
		
		private Product prod;
		
		@RequestMapping("/Customerlogin")
		public String login(@ModelAttribute Customer c, Model m )
		{
			cid=c.getCustomerid();
			boolean b=service.loginCustomer(c);
			if(b)
			{
			return "customermenu";
			}
			else
			{
				message = "Incorrect Login Credentials";
				m.addAttribute("message",message);
				return "message";
			}
		}
		
		@RequestMapping("/displaylist")
		public String getAllProducts(Model m)
		{
			List<Product> list=service.getAllProducts();
			m.addAttribute("list", list);
			return "displaylist";
		}
		
		@RequestMapping("/addToCart")
		public String AddToCart(@RequestParam int productno, Model m)
		{
			service.AddToCart(cid,productno);
			message = "Successfully product is added to Cart";
			m.addAttribute("message",message);
			return "message";
			
		}
		
		
		@RequestMapping("/displaycartlist")
		public String DisplayCartList(Model m)
		{
			List<Product> list=service.DisplayCartList(cid);
			m.addAttribute("list", list);
			return "displaycartlist";
		}
		
		@RequestMapping("/PayBill")
		public String PayBill(@ModelAttribute Product p)
		{
			prod=p;
			return "quantitycount";
			
		}
		
		@RequestMapping("/quantitycount")
		public String quantitycount(@RequestParam double quantity, Model m)
		{
			if(quantity<prod.getQuantity())
			{
				prod.setQuantity(quantity);
				m.addAttribute(quantity);
				m.addAttribute("Product",prod);
				return "displaybill";
			}
			else
			{
				message = "Quantity is Exceded";
				m.addAttribute("message",message);
				return "message";	
			}
				}
		
		@RequestMapping("/confirmpaybill")
		public String confirmpaybill(@ModelAttribute Product p, @RequestParam double finalcost, Model m)
		{
			boolean b=service.paybill(p,finalcost,cid);
			if(b==true)
			{
			message = "Successfully Bill Payed";
			m.addAttribute("message",message);
			return "message";	
			}
			else
			{
				message = "Insuffiecent Balance";
				m.addAttribute("message",message);
				return "message";	
			}
		}
		
		@RequestMapping("/addmoneytowallet")
		public String addmoneytowallet(@RequestParam double balance,Model m)
		{
			service.addmoneytowallet(cid,balance);
			message = "Successfully Amount Added To Wallet";
			m.addAttribute("message",message);
			return "message";
			
			
		}
		
		
		@RequestMapping("/showbalance")
		public String showBalance(@RequestParam int customerid, Model m)
		{
			double amount=service.showBalance(customerid);
			message = "balance is=Rs"+amount;
			m.addAttribute("message",message);
			return "message";
			
			
		}
		
}
