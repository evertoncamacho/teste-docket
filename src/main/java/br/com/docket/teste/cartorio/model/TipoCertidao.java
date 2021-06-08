package br.com.docket.teste.cartorio.model;

public enum TipoCertidao {
    NASCIMENTO("Certidão de Nascimento"),
    CASAMENTO("Certidão de Casamento"),
    OBITO("Certidão de Óbito");

    private String nome;

    TipoCertidao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
