package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.utils.HibernateUtils;

public class PurchaseProduct {

	public static void main(String[] args) {
		
		try(SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in))
		{
			ProductDao prodao = new ProductDaoImpl();
			
			System.out.println("For purchase:"
					+ "Enter product id and product quantity : ");
			
			System.out.println(prodao.purchaseProduct(sc.nextInt(), sc.nextInt()));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
