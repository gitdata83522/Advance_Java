package com.sunbeam.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;
import com.sunbeam.utils.HibernateUtils;



public class ProductDaoImpl implements ProductDao{
	
	String msg = "Product can't be added...";

	@Override
	public String addProduct(Product product) {
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		try {
			Serializable productId = session.save(product);
			
			msg = "Product added with product id : " + productId;
			
			ts.commit();
		}
		catch(RuntimeException e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
			
			throw e;
		}
		
		return msg;
	}

	
	
	
	@Override
	public Product getProductById(int id) {
		
		Product product = null;
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		
		try{
			
			product = session.get(Product.class, id);
			ts.commit();
			
		}
		catch(RuntimeException e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
				
			throw e;
		}
		
		return product;
	}




	@Override
	public List<Product> getProductBySpecifiedCategoryAndBetweenPriceRange(Category category, double firstPrice,
			double lastPrice) {
		
		String jpql = "Select p from Product p where p.category=:pc and p.price between :start and :end";
		
		List<Product> products = null;
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		try {
			
			products = session.createQuery(jpql, Product.class).setParameter("pc", category).setParameter("start", firstPrice).setParameter("end", lastPrice).getResultList();
		}
		catch(RuntimeException e)
		{
			if(ts != null)
				ts.rollback();
			
			throw e;
		}
		
		return products;
	}





	@Override
	public String applyCategoryWiseDiscount(Category category, double discount) {
		
		String jpql = "update Product p set p.price=p.price-:discount where p.category=:pc";
		
		String msg = "Discount not get applied";
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		try 
		{
			int rows = session.createQuery(jpql).setParameter("pc", category).setParameter("discount", discount).executeUpdate();
			
			ts.commit();
			
			msg = "No. of Products got discounted - " + rows;
		} 
		catch (RuntimeException e) 
		{
			if (ts != null)
				ts.rollback();
			
			throw e;
		}

		return msg;
		

	}




	@Override
	public String deleteProductByName(String productName) 
	{
		String jpql = "select p from Product p where p.name=:pn";
		
		Product product = null;
		
		String msg = "Product can't be deleted...";
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		try 
		{
			product = session.createQuery(jpql, Product.class).setParameter("pn", productName).getSingleResult();
			
			if(product != null)
			{
				session.delete(product);
				
				msg = "Product got deleted...";
				
				ts.commit();
			}
			
			
	
		}
		catch(RuntimeException e)
		{
			if(ts != null)
				ts.rollback();
			
			throw e;
		}
		
		
		return msg;
	}




	@Override
	public String purchaseProduct(int id, int quantity) 
	{
		//String jpql = "update Product p set p.quantity = p.quantity - :quantity where p.id = :id ";
		
		String msg = "Product can't be purchased...";
		
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
		Transaction ts = session.beginTransaction();
		
		int newQuantity = 0;
		
		Product product = null;
		
		try 
		{
			product = session.get(Product.class, id);
			
			System.out.println(product);
			
			
			if(product != null && product.getQuantity() > 0)
			{
				
				System.out.println("Product is available and quantity is " + product.getQuantity()); 
				
				
				if(product.getQuantity() > quantity)
				{
					
					newQuantity = product.getQuantity() - quantity;
					
					product.setQuantity(newQuantity);
					
					session.update(product);
					
					msg = "Product purchased successfully...";
					
					ts.commit();
				}
				else 
				{
					msg = "Pardon, we can't exceed our product quantity...";
					
					ts.rollback();
				}
				
				
			}
			else 
			{
				msg = "Product is not available...";
				
				ts.rollback();
			}
			
			
			
		}
		catch(RuntimeException e)
		{
			if(ts != null)
				ts.rollback();
				
			throw e;			
		}
		
		return msg;
	}	
}
