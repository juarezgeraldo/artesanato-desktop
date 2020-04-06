package com.br.juarezjunior.artesanato.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.juarezjunior.artesanato.entities.Pedido;
import com.br.juarezjunior.artesanato.entities.Pedido;
import com.br.juarezjunior.artesanato.repositories.PedidoRepository;
import com.br.juarezjunior.artesanato.services.exceptions.DatabaseException;
import com.br.juarezjunior.artesanato.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Pedido insert(Pedido obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pedido update(Long id, Pedido obj) {
		try {
			Pedido entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Pedido entity, Pedido obj) {
		entity.setData(obj.getData());
		entity.setStatusPedido(obj.getStatusPedido());
		entity.setFormaPagamento(obj.getFormaPagamento());
		entity.setCliente(obj.getCliente());
	}

}
