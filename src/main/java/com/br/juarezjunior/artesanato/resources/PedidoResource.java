package com.br.juarezjunior.artesanato.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.juarezjunior.artesanato.entities.Pedido;
import com.br.juarezjunior.artesanato.services.PedidoService;

@RestController
@RequestMapping (value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){

		List<Pedido> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> insert(@RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
