package com.br.juarezjunior.artesanato.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.juarezjunior.artesanato.entities.Categoria;
import com.br.juarezjunior.artesanato.entities.Cliente;
import com.br.juarezjunior.artesanato.entities.FormaPagamento;
import com.br.juarezjunior.artesanato.entities.Fornecedor;
import com.br.juarezjunior.artesanato.entities.ItemPedido;
import com.br.juarezjunior.artesanato.entities.Pagamento;
import com.br.juarezjunior.artesanato.entities.Pedido;
import com.br.juarezjunior.artesanato.entities.Produto;
import com.br.juarezjunior.artesanato.enums.StatusPedido;
import com.br.juarezjunior.artesanato.repositories.CategoriaRepository;
import com.br.juarezjunior.artesanato.repositories.ClienteRepository;
import com.br.juarezjunior.artesanato.repositories.FormaPagamentoRepository;
import com.br.juarezjunior.artesanato.repositories.FornecedorRepository;
import com.br.juarezjunior.artesanato.repositories.ItemPedidoRepository;
import com.br.juarezjunior.artesanato.repositories.PagamentoRepository;
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
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Override
	public void run(String... args) throws Exception {

		FormaPagamento fp1 = new FormaPagamento(null, "À vista");
		FormaPagamento fp2 = new FormaPagamento(null, "30 dias");
		FormaPagamento fp3 = new FormaPagamento(null, "60 dias");
		FormaPagamento fp4 = new FormaPagamento(null, "30/60 dias");

		Fornecedor f1 = new Fornecedor(null, "Fornecedor biju 1", 0.0, 0.0, "endereco", "observacao");
		Fornecedor f2 = new Fornecedor(null, "Fornecedor biju 2", 0.0, 0.0, "endereco", "observacao");
		
		Cliente cl1 = new Cliente(null, "Nome Cliente 1", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		Cliente cl2 = new Cliente(null, "Nome Cliente 2", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		Cliente cl3 = new Cliente(null, "Nome Cliente 3", 31984494471.0, 31999906793.0, "Rua José Antenor, 237 - apto. 401 - Heliópolis - 31.741-455 - Belo Horizonte - MG", "Cliente especial");
		
		Pedido p1 = new Pedido(null, Instant.parse("2020-04-01T15:48:00Z"), StatusPedido.PAGO, cl1, fp1);
		Pedido p2 = new Pedido(null, Instant.parse("2020-03-31T15:48:00Z"), StatusPedido.CONSIGNACAO, cl2, fp4);

		Pagamento pg1 = new Pagamento(null, Instant.parse("2020-04-01T16:00:00Z"), 50.0, p1);
		Pagamento pg2 = new Pagamento(null, Instant.parse("2020-04-10T16:00:00Z"), 20.0, p1);
		Pagamento pg3 = new Pagamento(null, Instant.parse("2020-04-08T16:00:00Z"), 45.0, p2);
		
		formaPagamentoRepository.saveAll(Arrays.asList(fp1, fp2, fp3, fp4));
		
		fornecedorRepository.saveAll(Arrays.asList(f1, f2));
		
		clienteRepository.saveAll(Arrays.asList(cl1,cl2,cl3));
		
		pedidoRepository.saveAll(Arrays.asList(p1,p2));

		pagamentoRepository.saveAll(Arrays.asList(pg1,pg2, pg3));

		
		Categoria c1 = new Categoria(null, "Brincos");
		Categoria c2 = new Categoria(null, "Colares");
		Categoria c3 = new Categoria(null, "Sandálias");
		
		Produto pr1 = new Produto(null, "Brinco de argola", 3, 5.00, 10.00, 0);
		Produto pr2 = new Produto(null, "Brinco de linha", 2, 5.00, 10.00, 0);
		Produto pr3 = new Produto(null, "Colar de pedras", 6, 5.00, 10.00, 0);
		Produto pr4 = new Produto(null, "Rasteirinha", 0, 5.00, 10.00, 0);
		Produto pr5 = new Produto(null, "Conjunto colar e brinco", 10, 7.50, 15.0, 1);
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3));
		produtoRepository.saveAll(Arrays.asList(pr1,pr2,pr3,pr4,pr5));

		pr5.getCategorias().add(c1);
		pr5.getCategorias().add(c2);
		
		produtoRepository.saveAll(Arrays.asList(pr5));
		
		ItemPedido ip1 = new ItemPedido(p1, pr5, 3, 15.0, 0.0);
		ItemPedido ip2 = new ItemPedido(p1, pr4, 2, 10.0, 0.0);
		ItemPedido ip3 = new ItemPedido(p2, pr1, 10, 10.0, 10.0);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		

//		f1.getProdutos().add(pr5);
//		f2.getProdutos().add(pr5);
//		
//		fornecedorRepository.saveAll(Arrays.asList(f1, f2));

		pr1.getFornecedores().add(f1);
		pr1.getFornecedores().add(f2);
		
		produtoRepository.saveAll(Arrays.asList(pr1));
		
	}

	
}
