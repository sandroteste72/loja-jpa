package br.com.arpitech.lojaJPA.dao;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.models.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Produto produto) {
		this.em.persist(produto);
	}
}
