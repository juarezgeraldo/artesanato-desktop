package com.br.juarezjunior.artesanato.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.juarezjunior.artesanato.entities.Pagamento;
import com.br.juarezjunior.artesanato.repositories.PagamentoRepository;
import com.br.juarezjunior.artesanato.services.exceptions.DatabaseException;
import com.br.juarezjunior.artesanato.services.exceptions.ResourceNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public List<Pagamento> findAll(){
		return repository.findAll();
	}
	
	public Pagamento findById(Long id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Pagamento insert(Pagamento obj) {
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

	public Pagamento update(Long id, Pagamento obj) {
		try {
			Pagamento entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Pagamento entity, Pagamento obj) {
		entity.setData(obj.getData());
		entity.setValor(obj.getValor());
	}

}
