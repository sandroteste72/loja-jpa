package br.com.arpitech.lojaJPA.tests;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.dao.CategoriaDao;
import br.com.arpitech.lojaJPA.dao.ClienteDao;
import br.com.arpitech.lojaJPA.dao.PedidoDao;
import br.com.arpitech.lojaJPA.dao.ProdutoDao;
import br.com.arpitech.lojaJPA.models.Categoria;
import br.com.arpitech.lojaJPA.models.Cliente;
import br.com.arpitech.lojaJPA.models.ItemPedido;
import br.com.arpitech.lojaJPA.models.Pedido;
import br.com.arpitech.lojaJPA.models.Produto;
import br.com.arpitech.lojaJPA.util.JPAUtil;
import br.com.arpitech.lojaJPA.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);		
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);		
		pedido.adicionarItem(new ItemPedido(2, pedido, produto3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal valorTotalVendido = pedidoDao.valorTotalVendido();
		System.out.println("------------------------------------------");
		System.out.println("Valor total vendido: " + valorTotalVendido);
		
		System.out.println("------------------------------------------");
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		System.out.println("------------------------------------------");
		relatorio.forEach(System.out::println);
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Iphone", "Iphone 13 PRO", new BigDecimal("7500"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("4500"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook Pro Retina", new BigDecimal("15000"), informatica);
		
		Cliente cliente = new Cliente("Sandro", "014.868.907-84");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
