package br.com.arpitech.lojaJPA.dao;

import javax.persistence.EntityManager;

import br.com.arpitech.lojaJPA.models.Categoria;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Categoria categoria) {
		this.em.persist(categoria);
	}
}
