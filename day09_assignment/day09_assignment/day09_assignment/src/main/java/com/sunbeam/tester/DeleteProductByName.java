package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.utils.HibernateUtils;

public class DeleteProductByName {

	public static void main(String[] args) 
	{
		try(SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in))
		{
			ProductDao prodao = new ProductDaoImpl();
			
			System.out.println("Enter product name to be deleted: ");
			
			System.out.println(prodao.deleteProductByName(sc.next()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
