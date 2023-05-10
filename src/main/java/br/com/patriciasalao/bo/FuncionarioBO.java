package br.com.patriciasalao.bo;

import br.com.patriciasalao.dao.CRUD;
import br.com.patriciasalao.dao.FuncionarioDAO;
import br.com.patriciasalao.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioBO implements CRUD<Funcionario, Long> {

    @Autowired
    private FuncionarioDAO dao;

    @Override
    public Funcionario pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Funcionario> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Funcionario funcionario) {
         dao.insere(funcionario);
    }

    @Override
    public void atualiza(Funcionario funcionario) {
        dao.atualiza(funcionario);
    }

    @Override
    public void remove(Funcionario funcionario) {
        dao.remove(funcionario);
    }

    public void inativa(Funcionario funcionario){
        funcionario.setAtivo(false);
        dao.atualiza(funcionario);
    }

    public void ativa(Funcionario funcionario){
        funcionario.setAtivo(true);
        dao.atualiza(funcionario);
    }
}
