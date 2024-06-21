package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Category;
import com.sunbeam.utils.HibernateUtils;

public class CategoryWiseDiscount {

	public static void main(String[] args) {
		
		try(SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in))
		{
			ProductDao prodao = new ProductDaoImpl();
			
			System.out.println("Enter category and discount amount: \n");
			
			System.out.println(prodao.applyCategoryWiseDiscount(Category.valueOf(sc.next().toUpperCase()), sc.nextDouble()));
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
