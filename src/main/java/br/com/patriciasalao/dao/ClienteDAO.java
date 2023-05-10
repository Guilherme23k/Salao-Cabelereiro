package br.com.patriciasalao.dao;

import br.com.patriciasalao.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ClienteDAO  implements CRUD<Cliente, Long>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Cliente pesquisaPeloId(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> listaTodos() {
        Query query = em.createQuery("SELECT c FROM Cliente c");
        return (List<Cliente>) query.getResultList();
    }

    @Override
    public void insere(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void atualiza(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void remove(Cliente cliente) {
        em.remove(cliente);
    }
}
