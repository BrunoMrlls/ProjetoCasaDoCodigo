package br.com.casadocodigo.loja.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository //mostrar para o spring que essa é a classe de persistencia
@Transactional //cuida da transação pra a persistencia
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Long id) {
		return manager.createQuery("Select distinct(p) from Produto p "
				+ "join fetch p.precos preco where p.id = :id", Produto.class).
				setParameter("id", id).getSingleResult();
	}
	
}
