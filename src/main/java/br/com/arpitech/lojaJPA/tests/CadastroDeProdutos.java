package br.com.arpitech.lojaJPA.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.dao.CategoriaDao;
import br.com.arpitech.lojaJPA.dao.ProdutoDao;
import br.com.arpitech.lojaJPA.models.Categoria;
import br.com.arpitech.lojaJPA.models.Produto;
import br.com.arpitech.lojaJPA.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Samsung A32", "Camera 64 megapixels 128 GB memoria", new BigDecimal("1500"), celulares);

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
