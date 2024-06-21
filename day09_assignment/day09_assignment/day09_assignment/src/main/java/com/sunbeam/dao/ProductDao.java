package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

public interface ProductDao 
{
	String addProduct(Product product);
	
	Product getProductById(int id);
	
	List<Product> getProductBySpecifiedCategoryAndBetweenPriceRange(Category category, double firstPrice, double lastPrice);

	String applyCategoryWiseDiscount(Category category, double discount);
	
	String deleteProductByName(String name);

	String purchaseProduct(int id, int quantity);
}
