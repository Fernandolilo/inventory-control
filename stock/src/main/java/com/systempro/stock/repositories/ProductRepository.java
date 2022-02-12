package com.systempro.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.systempro.stock.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{	
	
	@Transactional(readOnly = true)
	Product findByBarCode(String barCode);
}
