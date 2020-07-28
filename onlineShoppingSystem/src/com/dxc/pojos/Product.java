package com.dxc.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product 
{
@Id
private int productno;
private String productname;
private double productcost;
private double quantity;
private float discount;

public Product()
{
	
}

public Product(int productno, String productname, double productcost, double quantity, float discount) {
	super();
	this.productno = productno;
	this.productname = productname;
	this.productcost = productcost;
	this.quantity = quantity;
	this.discount = discount;
}

public int getProductno() {
	return productno;
}

public void setProductno(int productno) {
	this.productno = productno;
}

public String getProductname() {
	return productname;
}

public void setProductname(String productname) {
	this.productname = productname;
}

public double getProductcost() {
	return productcost;
}

public void setProductcost(double productcost) {
	this.productcost = productcost;
}

public double getQuantity() {
	return quantity;
}

public void setQuantity(double quantity) {
	this.quantity = quantity;
}

public float getDiscount() {
	return discount;
}

public void setDiscount(float discount) {
	this.discount = discount;
}

@Override
public String toString() {
	return "Product [productno=" + productno + ", productname=" + productname + ", productcost=" + productcost
			+ ", quantity=" + quantity + ", discount=" + discount + "]";
}

}
