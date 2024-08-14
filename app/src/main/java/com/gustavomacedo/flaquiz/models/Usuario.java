package com.gustavomacedo.flaquiz.models;

public class Usuario {
    private String nome;
    private int qtdCorretas;
    private int qtdIncorretas;

    public Usuario() {}

    public Usuario(String nome, int qtdCorretas, int qtdIncorretas) {
        this.nome = nome;
        this.qtdCorretas = qtdCorretas;
        this.qtdIncorretas = qtdIncorretas;
    }

    public boolean temAcertosMaioresQueErros() {
        return qtdCorretas > qtdIncorretas;
    }

    public int incrementaCorreta() {
        this.qtdCorretas++;
        return qtdCorretas;
    }

    public int incrementaIncorreta() {
        this.qtdIncorretas++;
        return qtdIncorretas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdCorretas() {
        return qtdCorretas;
    }

    public void setQtdCorretas(int qtdCorretas) {
        this.qtdCorretas = qtdCorretas;
    }

    public int getQtdIncorretas() {
        return qtdIncorretas;
    }

    public void setQtdIncorretas(int qtdIncorretas) {
        this.qtdIncorretas = qtdIncorretas;
    }

    public int diferencaAcertosErros() {
        return qtdCorretas - qtdIncorretas;
    }
}
