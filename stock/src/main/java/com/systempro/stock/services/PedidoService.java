package com.systempro.stock.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.systempro.stock.entities.ItemPedido;
import com.systempro.stock.entities.Pedido;
import com.systempro.stock.repositories.ItemPedidoRepository;
import com.systempro.stock.repositories.PedidoRepository;
import com.systempro.stock.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private final ItemPedidoRepository itemPedidoRepository;
	private final ProductService productService;
	private final PedidoRepository repository;

	@Autowired
	public PedidoService(ItemPedidoRepository itemPedidoRepository, ProductService productService,
			PedidoRepository repository) {
		this.itemPedidoRepository = itemPedidoRepository;
		this.productService = productService;
		this.repository = repository;
	}

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! : Id: " + "Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj = repository.save(obj);
		for (ItemPedido ip : obj.getItens()) {
			ip.setProduct(productService.findById(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setAmount(ip.getAmount());
			ip.getProduct().removeAmout(ip.getAmount());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}
