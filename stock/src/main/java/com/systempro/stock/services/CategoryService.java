package com.systempro.stock.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Category;
import com.systempro.stock.entities.dto.CategoryDTO;
import com.systempro.stock.repositories.CategoryRepeository;
import com.systempro.stock.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	private final CategoryRepeository repository;

	@Autowired
	public CategoryService(CategoryRepeository repository) {
		this.repository = repository;
	}

	public Category findById(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + " , Tipo: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	// para fazer um POST em CategoriaResource
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}

}
