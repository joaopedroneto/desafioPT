package br.com.juridico.totvs.fullstack.Backend.service.dto;

public class PaisCreateUpdateDTO {
    private String nome;
    private String sigla;
    private String continente;
    private int ddi;

    public PaisCreateUpdateDTO(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public int getDdi() {
        return ddi;
    }

    public void setDdd(int ddi) {
        this.ddi = ddi;
    }
}
