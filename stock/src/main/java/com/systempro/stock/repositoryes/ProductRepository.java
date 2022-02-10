package com.systempro.stock.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.stock.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
