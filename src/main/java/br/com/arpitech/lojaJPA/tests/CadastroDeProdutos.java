package br.com.arpitech.lojaJPA.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.dao.ProdutoDao;
import br.com.arpitech.lojaJPA.models.Categoria;
import br.com.arpitech.lojaJPA.models.Produto;
import br.com.arpitech.lojaJPA.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		Produto celular = new Produto("Samsung A32", "Camera 64 megapixels 128 GB memoria", new BigDecimal("1500"), Categoria.CELULARES);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
