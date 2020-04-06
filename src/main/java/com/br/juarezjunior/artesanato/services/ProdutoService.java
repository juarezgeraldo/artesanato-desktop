package com.br.juarezjunior.artesanato.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.juarezjunior.artesanato.entities.Produto;
import com.br.juarezjunior.artesanato.entities.Produto;
import com.br.juarezjunior.artesanato.repositories.ProdutoRepository;
import com.br.juarezjunior.artesanato.services.exceptions.DatabaseException;
import com.br.juarezjunior.artesanato.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	public Produto insert(Produto obj) {
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

	public Produto update(Long id, Produto obj) {
		try {
			Produto entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setCustoMedio(obj.getCustoMedio());
		entity.setEstoqueMinimo(obj.getEstoqueMinimo());
		entity.setPrecoVenda(obj.getPrecoVenda());
	}

}
