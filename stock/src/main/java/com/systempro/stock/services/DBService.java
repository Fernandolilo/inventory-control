package com.systempro.stock.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Category;
import com.systempro.stock.repositoryes.CategoryRepeository;

@Service
public class DBService {

	private final CategoryRepeository categoryRepeository;
	
	@Autowired
	public DBService(CategoryRepeository categoryRepeository) {
		this.categoryRepeository = categoryRepeository;
	}

	public void instantiateDatabase() {

		Category cat1 = new Category(null, "whisky´s");
		Category cat2 = new Category(null, "Cervejas");
		Category cat3 = new Category(null, "Refrigerantes");
		
		
		categoryRepeository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}

}
