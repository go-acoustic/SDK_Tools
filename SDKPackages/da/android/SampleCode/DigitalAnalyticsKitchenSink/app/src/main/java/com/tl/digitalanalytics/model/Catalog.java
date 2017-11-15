/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.model;

import java.io.InputStream;
import java.io.Serializable;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Context;

/**
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class Catalog implements Serializable
{
	private static final long serialVersionUID = 8750107998880620211L;
	
	private static volatile Catalog singleton;
	
	private Category[] categories;

	private Catalog(Context context,String source)
	{
		if(source == null || source.trim().length() == 0)
		{
			throw new IllegalArgumentException("Catalog Source must be specified");
		}
		
		this.loadCatalog(context,source);
	}
	
	public static Catalog getInstance(Context context, String source)
	{
		if(singleton == null)
		{
			singleton = new Catalog(context,source);
		}
		return singleton;
	}
	
	public static Catalog getInstance()
	{
		return singleton;
	}
	
	public Category[] getCategories()
	{
		return this.categories;
	}
	
	public Category getCategory(String categoryId)
	{
		if(this.categories == null)
		{
			return null;
		}
		
		for(Category category:this.categories)
		{
			if(category.getId().equals(categoryId))
			{
				return category;
			}
		}
		
		return null;
	}
	
	private void loadCatalog(Context context, String source)
	{
		InputStream is = null;
		try
		{
			//Parser setup
			is = context.getResources().getAssets().open(source);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document dom = builder.parse(is);
			
			//Let the parsing begin
			NodeList catNodes = dom.getElementsByTagName("category");
			int catNodeCount = catNodes.getLength();
			this.categories = new Category[catNodeCount];
			
			for(int i=0; i<catNodeCount; i++)
			{
				Element catElem = (Element)catNodes.item(i);
				this.categories[i] = this.parseCategory(catElem);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			if(is != null)
			{
				try{is.close();}catch(IOException ioe){
					//don't worry...we tried to clean up
				}
			}
		}
	}
	
	private Category parseCategory(Element catElem) throws Exception
	{
		Category category = new Category();
		
		String id = catElem.getElementsByTagName("id").item(0).getTextContent();
		String name = catElem.getElementsByTagName("name").item(0).getTextContent();
		
		NodeList prodNodes = catElem.getElementsByTagName("product");
		int prodCount = prodNodes.getLength();
		Product[] products = new Product[prodCount];
		for(int i=0; i<prodCount; i++)
		{
			Element prodElem = (Element)prodNodes.item(i);
			products[i] = this.parseProduct(prodElem, id);
		}
		
		//populate the category object
		category.setId(id);
		category.setName(name);
		category.setProducts(products);
		
		return category;
	}
	
	private Product parseProduct(Element prodElem, String categoryId) throws Exception
	{
		Product product = new Product();
		
		String id = prodElem.getElementsByTagName("id").item(0).getTextContent();
		String name = prodElem.getElementsByTagName("name").item(0).getTextContent();
		String baseUnitPrice = prodElem.getElementsByTagName("baseUnitPrice").item(0).getTextContent();
		
		
		//Parse out the attributes associated with this product
		NodeList attrNodes = prodElem.getElementsByTagName("attribute");
		int attrCount = attrNodes.getLength();
		String[] attributes = new String[attrCount];
		for(int i=0; i<attrCount; i++)
		{
			attributes[i] = attrNodes.item(i).getTextContent();
		}
		
		
		//populate the product object
		product.setId(id);
		product.setName(name);
		product.setBaseUnitPrice(baseUnitPrice);
		product.setAttributes(attributes);
		product.setCategoryId(categoryId);
		
		return product;
	}
}
