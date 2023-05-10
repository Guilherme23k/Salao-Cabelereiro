package br.com.patriciasalao.model;

public enum TipoServico {

    PROGRESSIVA("Progressiva"),
    CORTE("Corte"),
    LUZES("Luzes");

    private String descricao;

    TipoServico(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

}
