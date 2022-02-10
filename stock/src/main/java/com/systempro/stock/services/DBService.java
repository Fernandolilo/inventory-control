package com.systempro.stock.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Category;
import com.systempro.stock.entities.Product;
import com.systempro.stock.repositoryes.CategoryRepeository;
import com.systempro.stock.repositoryes.ProductRepository;

@Service
public class DBService {

	private final CategoryRepeository categoryRepeository;
	private final ProductRepository productRepository;

	@Autowired
	public DBService(CategoryRepeository categoryRepeository, ProductRepository productRepository) {
		this.categoryRepeository = categoryRepeository;
		this.productRepository = productRepository;
	}

	public void instantiateDatabase() {

		Category cat1 = new Category(null, "whisky´s");
		Category cat2 = new Category(null, "Cervejas");
		Category cat3 = new Category(null, "Refrigerantes");
		
		Product p1 = new Product(null, "123123", "Chivas", 150.00, 10, cat1);
		Product p2 = new Product(null, "123123", "Red", 150.00, 10, cat1);
		Product p3 = new Product(null, "123123", "Skol", 3.50, 10, cat2);
		Product p4 = new Product(null, "123123", "Corona", 3.50, 10, cat2);
		Product p5 = new Product(null, "123123", "Coca-cola", 10.00, 10, cat3);
		Product p6 = new Product(null, "123123", "Fanta - laranja", 10.00, 10, cat3);

		cat1.getProducts().addAll(Arrays.asList(p1, p2));
		cat2.getProducts().addAll(Arrays.asList(p3, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		categoryRepeository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
	}

}
