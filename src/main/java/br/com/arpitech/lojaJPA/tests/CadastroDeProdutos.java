package br.com.arpitech.lojaJPA.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.arpitech.lojaJPA.models.Produto;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("Samsung A32");
		celular.setDescricao("Camera 64 megapixels 128 GB memoria");
		celular.setPreco(new BigDecimal("1500"));

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lojajpa");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}
}
