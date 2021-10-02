package br.gov.sp.fatec.lojadediscos.controller.dto;

public class PostFaixaDTO {
    private String nome;
    private Integer duracao;

    public PostFaixaDTO() {
    }

    public PostFaixaDTO(String nome, Integer duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}
