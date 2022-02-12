package com.systempro.stock.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Product;
import com.systempro.stock.entities.dto.ProductDTO;
import com.systempro.stock.repositories.ProductRepository;
import com.systempro.stock.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final CategoryService categoryService;

	@Autowired
	public ProductService(ProductRepository repository, CategoryService categoryService) {
		this.repository = repository;
		this.categoryService = categoryService;
	}

	public Product findById(Integer id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + " , Tipo: " + Product.class.getName()));
	}

	public Product insert(Product obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Product update(Product obj) {
		Product newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Product newObj, Product obj) {
		newObj.setName(obj.getName());
	}

	public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	// para fazer um POST em CategoriaResource
	public Product fromDTO(ProductDTO obj) {
		return new Product(obj.getId(), obj.getBarCode(), obj.getName(), obj.getPrice(), obj.getAmount(),
				categoryService.findById(obj.getCategory().getId()));
	}

}
