package com.systempro.stock.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.Category;
import com.systempro.stock.entities.Pedido;
import com.systempro.stock.entities.ItemPedido;
import com.systempro.stock.entities.Product;
import com.systempro.stock.repositories.CategoryRepeository;
import com.systempro.stock.repositories.PedidoRepository;
import com.systempro.stock.repositories.ItemPedidoRepository;
import com.systempro.stock.repositories.ProductRepository;

@Service
public class DBService {
	private final CategoryRepeository categoryRepeository;
	private final ProductRepository productRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final PedidoRepository pedidoRepository;

	@Autowired
	public DBService(CategoryRepeository categoryRepeository, ProductRepository productRepository,
			ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository) {
		this.categoryRepeository = categoryRepeository;
		this.productRepository = productRepository;
		this.itemPedidoRepository = itemPedidoRepository;
		this.pedidoRepository = pedidoRepository;
	}

	public void instantiateDatabase() throws ParseException {

		Category cat1 = new Category(null, "whisky´s");
		Category cat2 = new Category(null, "Cervejas");
		Category cat3 = new Category(null, "Refrigerantes");

		Product p1 = new Product(null, "12", "Chivas", 150.00, 10, cat1);
		Product p2 = new Product(null, "123", "Red", 150.00, 10, cat1);
		Product p3 = new Product(null, "1234", "Skol", 3.50, 10, cat2);
		Product p4 = new Product(null, "12345", "Corona", 3.50, 10, cat2);
		Product p5 = new Product(null, "123456", "Coca-cola", 10.00, 10, cat3);
		Product p6 = new Product(null, "1234567", "Fanta - laranja", 10.00, 10, cat3);

		cat1.getProducts().addAll(Arrays.asList(p1, p2));
		cat2.getProducts().addAll(Arrays.asList(p3, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		categoryRepeository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido or1 = new Pedido(null, sdf.parse("25/05/2021 18:47"));
		Pedido or2 = new Pedido(null, sdf.parse("25/05/2021 18:47"));
		Pedido or3 = new Pedido(null, sdf.parse("25/05/2021 18:47"));	
		
		
		ItemPedido it1 = new ItemPedido(or1, p1, p1.getPrice(), 3);
		ItemPedido it2 = new ItemPedido(or2, p2, p2.getPrice(), 1);
		ItemPedido it3 = new ItemPedido(or2, p3, p3.getPrice(), 4);
		ItemPedido it4 = new ItemPedido(or1, p4, p4.getPrice(), 12);
		ItemPedido it5 = new ItemPedido(or1, p5, p5.getPrice(), 1);
		ItemPedido it6 = new ItemPedido(or3, p6, p6.getPrice(), 5);

		or1.getItens().addAll(Arrays.asList(it1, it4, it5));
		or2.getItens().addAll(Arrays.asList(it2, it3));
		or3.getItens().add(it6);

		pedidoRepository.saveAll(Arrays.asList(or1, or2, or3));
		itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5, it6));
	}

}
