package com.systempro.stock.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.systempro.stock.entities.Category;
import com.systempro.stock.entities.Product;
import com.systempro.stock.entities.dto.CategoryDTO;
import com.systempro.stock.entities.dto.ProductDTO;
import com.systempro.stock.repositories.CategoryRepeository;
import com.systempro.stock.repositories.ProductRepository;

@Component
public class ProductReceiveMessage {

	private final ProductRepository productRepository;
	private final CategoryRepeository categoryRepeository;
	
	@Autowired
	public ProductReceiveMessage(ProductRepository productRepository, CategoryRepeository categoryRepeository) {
		this.productRepository = productRepository;
		this.categoryRepeository = categoryRepeository;
	}
	
	@RabbitListener(queues = { "ProdutocontrolStock.ProdutocontrolStock.queue" })
	public void receive(@Payload ProductDTO produtoDTO) {
		productRepository.save(Product.create(produtoDTO));
	}
	
	@RabbitListener(queues = { "ProdutocontrolStock.ProdutocontrolStock.queue" })
	public void receive(@Payload CategoryDTO categoryDTO) {
		categoryRepeository.save(Category.create(categoryDTO));
	}
}
