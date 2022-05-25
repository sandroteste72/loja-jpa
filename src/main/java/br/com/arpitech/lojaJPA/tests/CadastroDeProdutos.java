package br.com.arpitech.lojaJPA.tests;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.dao.CategoriaDao;
import br.com.arpitech.lojaJPA.dao.ProdutoDao;
import br.com.arpitech.lojaJPA.models.Categoria;
import br.com.arpitech.lojaJPA.models.Produto;
import br.com.arpitech.lojaJPA.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()));
		
		List<Produto> porNome = produtoDao.buscarPorNome("Samsung A32");
		porNome.forEach(p2 -> System.out.println(p.getNome() + ", R$ " + p.getPreco()));
		
		List<Produto> porCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		porCategoria.forEach(p2 -> System.out.println(p.getNome() + ", " + p.getCategoria().getNome()));
		
		BigDecimal precoDoProdutoComNome = produtoDao.buscarPrecoDoProduto("Iphone 13 PRO");
		System.out.println(precoDoProdutoComNome);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Iphone 13 PRO", "Camera 64 megapixels 128 GB memoria", new BigDecimal("1500"),
				celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
