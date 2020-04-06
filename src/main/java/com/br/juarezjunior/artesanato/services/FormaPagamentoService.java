package com.br.juarezjunior.artesanato.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.juarezjunior.artesanato.entities.FormaPagamento;
import com.br.juarezjunior.artesanato.repositories.FormaPagamentoRepository;
import com.br.juarezjunior.artesanato.services.exceptions.DatabaseException;
import com.br.juarezjunior.artesanato.services.exceptions.ResourceNotFoundException;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	public List<FormaPagamento> findAll(){
		return repository.findAll();
	}
	
	public FormaPagamento findById(Long id) {
		Optional<FormaPagamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public FormaPagamento insert(FormaPagamento obj) {
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

	public FormaPagamento update(Long id, FormaPagamento obj) {
		try {
			FormaPagamento entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(FormaPagamento entity, FormaPagamento obj) {
		entity.setNome(obj.getNome());
	}

}
