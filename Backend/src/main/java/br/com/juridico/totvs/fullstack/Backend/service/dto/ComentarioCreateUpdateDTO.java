package br.com.juridico.totvs.fullstack.Backend.service.dto;

public class ComentarioCreateUpdateDTO {
    private Long idPT;
    private String nome;
    private String comen;
    private String data;

    public ComentarioCreateUpdateDTO(){

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

    public void setContinente(String data) {
        this.data = data;
    }
}
