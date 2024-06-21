package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.utils.HibernateUtils;

public class TestHibernate 
{
	
	public static void main(String[] args) {
		
		try(SessionFactory sf = HibernateUtils.getFactory())
		{
			System.out.println("Hibernate jaag gya and working....");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
