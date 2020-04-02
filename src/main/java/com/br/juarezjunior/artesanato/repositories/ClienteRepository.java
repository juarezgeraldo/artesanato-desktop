package com.br.juarezjunior.artesanato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.juarezjunior.artesanato.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
