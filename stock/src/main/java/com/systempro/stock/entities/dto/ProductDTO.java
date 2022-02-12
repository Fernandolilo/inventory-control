package com.systempro.stock.entities.dto;

import java.io.Serializable;

import com.systempro.stock.entities.Category;
import com.systempro.stock.entities.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String barCode;
	private String name;
	private Double price;
	private Integer amount;
	private Category category;

	public ProductDTO() {
	}

	public ProductDTO(Product obj) {
		id = obj.getId();
		barCode = obj.getBarCode();
		name = obj.getName();
		price = obj.getPrice();
		amount = obj.getAmount();
		category = obj.getCategory();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
