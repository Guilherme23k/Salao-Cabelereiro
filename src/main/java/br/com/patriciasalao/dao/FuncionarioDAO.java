package br.com.patriciasalao.dao;


import br.com.patriciasalao.model.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class FuncionarioDAO implements CRUD<Funcionario, Long>{

    @PersistenceContext
    private EntityManager em;


    @Override
    public Funcionario pesquisaPeloId(Long id) {
        return em.find(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> listaTodos() {
        Query query = em.createQuery("SELECT f FROM Funcionario f");
        return (List<Funcionario>) query.getResultList();
    }

    @Override
    public void insere(Funcionario funcionario) {
        em.persist(funcionario);
    }

    @Override
    public void atualiza(Funcionario funcionario) {
        em.merge(funcionario);
    }

    @Override
    public void remove(Funcionario funcionario) {
        em.remove(funcionario);
    }
}
