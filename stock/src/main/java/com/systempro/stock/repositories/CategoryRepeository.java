package com.systempro.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.stock.entities.Category;

@Repository
public interface CategoryRepeository extends JpaRepository<Category, Integer>{

}
