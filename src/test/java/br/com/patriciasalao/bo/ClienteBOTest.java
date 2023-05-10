package br.com.patriciasalao.bo;

import br.com.patriciasalao.model.Cliente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final public class ClienteBOTest {

    @Autowired
    private ClienteBO bo;

    @Test
    @Order(1)
    public void insere(){
        Cliente cliente = new Cliente();
        cliente.setNome("Guilherme Gonçalves");
        cliente.setUltimaCompra(LocalDateTime.of(2000,1, 8, 10, 30));
        cliente.setCelular("11992834751");
        cliente.setAtivo(true);
        bo.insere(cliente);
    }

    @Test
    @Order(2)
    public void pesquisaPeloId(){
        Cliente cliente = bo.pesquisaPeloId(1L);
        System.out.println(cliente);
    }

    @Test
    @Order(3)
    public void atualiza(){
        Cliente cliente = bo.pesquisaPeloId(1L);
        cliente.setCelular("88888888888");
        bo.atualiza(cliente);
    }

    @Test
    @Order(4)
    public void lista(){
        List<Cliente> clientes = bo.listaTodos();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    @Test
    @Order(5)
    public void inativa(){
        Cliente cliente = bo.pesquisaPeloId(1L);
        bo.inativa(cliente);
    }

    @Test
    @Order(6)
    public void remove(){
        Cliente cliente = bo.pesquisaPeloId(1L);
        bo.remove(cliente);
    }
}
