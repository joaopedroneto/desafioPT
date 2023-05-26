package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;

public class ComentarioDTO {
    private Long id;
    private Long idPT;
    private String nome;
    private String comen;
    private String data;

    public ComentarioDTO(Long id,Long idPT, String nome, String comen, String data){
        this.id = id;
        this.idPT = idPT;
        this.nome = nome;
        this.comen = comen;
        this.data = data;
    }

    public ComentarioDTO(Comentario comentario){
        this.id = comentario.getId();
        this.idPT = comentario.getIdPT();
        this.nome = comentario.getNome();
        this.comen = comentario.getComen();
        this.data = comentario.getData();
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
