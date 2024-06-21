package com.sunbeam.tester;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;

public class GetProductById {
	
	public static void main(String[] args) {
		
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			
			ProductDao prodao = new ProductDaoImpl();
			
			System.out.println("Enter product id : ");
			
			int id = sc.nextInt();

			System.out.println(prodao.getProductById(id));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
