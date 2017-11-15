/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.model;

import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import java.io.Serializable;

/**
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class ShoppingCart implements Serializable 
{
	private static final long serialVersionUID = 3440631641974197052L;
	private static volatile ShoppingCart singleton;
	
	private Set<Product> products;
	
	private ShoppingCart()
	{
		this.products = new HashSet<Product>();
	}
	
	public static ShoppingCart getInstance()
	{
		if(singleton == null)
		{
			singleton = new ShoppingCart();
		}
		return singleton;
	}
	
	public void addToCart(Product product)
	{
		this.products.remove(product);
		if(product.getQuantity() > 0)
		{
			this.products.add(product);
		}
	}
	
	public Product[] getProducts() {
		return this.products.toArray(new Product[this.products.size()]);
	}
	
	public float calculateSubtotal()
	{
		float subtotal = 0.00f;
		
		for(Product product:this.products)
		{
			subtotal += product.getTotalPrice();
		}
		
		return subtotal;
	}
	
	public boolean isEmpty()
	{
		return this.products.isEmpty();
	}
	
	public Order generateOrder()
	{
		Order order = new Order();
		
		//generate a random order id
		order.setId("/kitchensink/new/mobilesdk"+"/"+UUID.randomUUID().toString());
		
		
		//Populate data we already know from the cart
		order.setSubTotal(""+this.calculateSubtotal());
		
		//Hard coded data...no need to complicate the user experience to collect this data
		order.setCurrencyCode("USD");
		order.setShippingCharge("5.00");
		order.setAttributes(new String[]{"grocery","shopping"});
		
		return order;
	}
	
	public void clear()
	{
		this.products.clear();
	}
}
