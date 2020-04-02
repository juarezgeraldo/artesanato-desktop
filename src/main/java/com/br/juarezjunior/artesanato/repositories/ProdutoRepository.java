package com.br.juarezjunior.artesanato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.juarezjunior.artesanato.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
