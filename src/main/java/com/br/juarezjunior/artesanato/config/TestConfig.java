package com.br.juarezjunior.artesanato.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.juarezjunior.artesanato.entities.Categoria;
import com.br.juarezjunior.artesanato.entities.Cliente;
import com.br.juarezjunior.artesanato.entities.Pedido;
import com.br.juarezjunior.artesanato.entities.Produto;
import com.br.juarezjunior.artesanato.enums.StatusPedido;
import com.br.juarezjunior.artesanato.repositories.CategoriaRepository;
import com.br.juarezjunior.artesanato.repositories.ClienteRepository;
import com.br.juarezjunior.artesanato.repositories.PedidoRepository;
import com.br.juarezjunior.artesanato.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@Override
	public void run(String... args) throws Exception {
		Cliente cl1 = new Cliente(null, "Nome Cliente 1", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		Cliente cl2 = new Cliente(null, "Nome Cliente 2", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		Cliente cl3 = new Cliente(null, "Nome Cliente 3", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		
		Pedido p1 = new Pedido(null, Instant.parse("2020-04-01T15:48:00Z"), StatusPedido.PAGO, cl1);
		Pedido p2 = new Pedido(null, Instant.parse("2020-03-31T15:48:00Z"), StatusPedido.CONSIGNACAO, cl2);

		clienteRepository.saveAll(Arrays.asList(cl1,cl2,cl3));
		
		pedidoRepository.saveAll(Arrays.asList(p1,p2));

		Categoria c1 = new Categoria(null, "Brincos");
		Categoria c2 = new Categoria(null, "Colares");
		Categoria c3 = new Categoria(null, "Sandálias");
		
		Produto pr1 = new Produto(null, "Brinco de argola", 5.00, 10.00, 0);
		Produto pr2 = new Produto(null, "Brinco de linha", 5.00, 10.00, 0);
		Produto pr3 = new Produto(null, "Colar de pedras", 5.00, 10.00, 0);
		Produto pr4 = new Produto(null, "Rasteirinha", 5.00, 10.00, 0);
		Produto pr5 = new Produto(null, "Conjunto colar e brinco", 7.50, 15.0, 1);
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3));
		produtoRepository.saveAll(Arrays.asList(pr1,pr2,pr3,pr4,pr5));

		pr5.getCategorias().add(c1);
		pr5.getCategorias().add(c2);
		
		produtoRepository.saveAll(Arrays.asList(pr5));
		

		
		
	}

	
}
