package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Pais;

public class PaisDTO {
    private Long id;
    private String nome;
    private String sigla;
    private String continente;
    private int ddi;

    public PaisDTO(Long id, String nome, String sigla, String continente, int ddd){
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.continente = continente;
        this.ddi = ddi;
    }

    public PaisDTO(Pais pais){
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.continente = pais.getContinente();
        this.sigla = pais.getSigla();
        this.ddi = pais.getDdi();
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
