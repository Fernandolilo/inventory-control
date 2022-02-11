package com.systempro.stock.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Category;
import com.systempro.stock.repositoryes.CategoryRepeository;
import com.systempro.stock.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	private final CategoryRepeository repeository;

	@Autowired
	public CategoryService(CategoryRepeository repeository) {
		this.repeository = repeository;
	}
	
	
	public Category findById(Integer id) {
		Optional<Category> obj = repeository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + " , Tipo: " + Category.class.getName()));
	}

}
