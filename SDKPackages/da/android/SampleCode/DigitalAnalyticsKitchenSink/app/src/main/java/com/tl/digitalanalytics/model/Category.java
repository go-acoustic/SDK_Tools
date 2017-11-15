/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.model;

import java.io.Serializable;

/**
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class Category implements Serializable
{
	private static final long serialVersionUID = -8182535149896063419L;
	
	private String id;
	private String name;
	private Product[] products;
	
	public Category()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}
}
