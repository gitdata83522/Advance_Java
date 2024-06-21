package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Category;
import com.sunbeam.utils.HibernateUtils;

public class GetProductBySpecifiedCategoryAndBetweenPriceRange{
	
	public static void main(String[] args) 
	{
		
		try(SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in))
		{
			ProductDao prodao = new ProductDaoImpl();
			
			System.out.println("Product's details: \n"
					+ "Enter category: \n"
					+ "Enter price range: \n");
		
			prodao.getProductBySpecifiedCategoryAndBetweenPriceRange(Category.valueOf(sc.next().toUpperCase()), sc.nextDouble(), sc.nextDouble())
			.forEach(System.out::println);
			
			
			//prodao.getProductBySpecifiedCategoryAndBetweenPriceRange(Category.valueOf(sc.next().toUpperCase()), sc.nextDouble(), sc.nextDouble());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}

