package br.com.juridico.totvs.fullstack.Backend.domain;

import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;

public class Comentario {

    private Long id;
    private Long idPT;
    private String nome;
    private String comen;
    private String data;

    public Comentario(Long id,Long idPT, String nome, String comen, String data){
        this.id = id;
        this.idPT = idPT;
        this.nome = nome;
        this.comen = comen;
        this.data = data;
    }

    public Comentario(ComentarioDTO comentarioDTO){
        this.id = comentarioDTO.getId();
        this.idPT = comentarioDTO.getIdPT();
        this.nome = comentarioDTO.getNome();
        this.comen = comentarioDTO.getComen();
        this.data = comentarioDTO.getData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdPT() {
        return idPT;
    }

    public void setIdPT(Long idPT) {
        this.idPT = idPT;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComen() {
        return comen;
    }

    public void setComen(String comen) {
        this.comen = comen;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
