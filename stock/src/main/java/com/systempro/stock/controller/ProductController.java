package com.systempro.stock.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.systempro.stock.entities.Product;
import com.systempro.stock.entities.dto.ProductDTO;
import com.systempro.stock.services.ProductService;

@RestController
@RequestMapping("/producties")
public class ProductController {

	private final ProductService service;

	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProductDTO objDto) {
		Product obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		// URI serve para pegar a URI gerada no insert do objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProductDTO objDto, @PathVariable Integer id) {
		Product obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		// pega a lista de categorias
		Page<Product> list = service.findPage(page, linesPerPage, orderBy, direction);
		// converte a lista de categorias para um ProductDTO
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		// retorna apenas dados nocessários.
		return ResponseEntity.ok().body(listDto);
	}
}
