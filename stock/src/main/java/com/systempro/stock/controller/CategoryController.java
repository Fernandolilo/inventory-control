package com.systempro.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systempro.stock.entities.Category;
import com.systempro.stock.services.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private final CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById (@PathVariable Integer id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
