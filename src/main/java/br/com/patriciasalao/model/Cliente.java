package br.com.patriciasalao.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 11)
    private String celular;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable=false, name="ultimaCompra", columnDefinition = "DATETIME")
    private LocalDateTime ultimaCompra = LocalDateTime.now();


    private boolean ativo;

    public void addServico(Servico servico){
        this.ultimaCompra = servico.getUltimaCompra();
    }

    public Cliente(){
        this.ativo = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDateTime ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }


    @Override
    public String toString(){
        String cliente = "";
        cliente +="Cliente\n";
        cliente +="-------------------------------------\n";
        cliente +="ID...............: " + this.id + "\n";
        cliente +="Nome.............: " + this.nome + "\n";
        cliente +="Celular..........: " + this.celular + "\n";
        cliente +="Ult. Compra......: " + this.ultimaCompra + "\n";
        cliente +="Ativo............: " + (this.ativo ? "Sim" : "Não") + "\n";
        return cliente;
    }


}
