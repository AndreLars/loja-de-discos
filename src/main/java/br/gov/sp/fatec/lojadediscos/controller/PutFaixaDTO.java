package br.gov.sp.fatec.lojadediscos.controller;

public class PutFaixaDTO {
    private Long faixaId;
    private String nome;
    private Integer duracao;
    private Integer ordem;

    public PutFaixaDTO() {
    }

    public PutFaixaDTO(Long faixaId, String nome, Integer duracao, Integer ordem) {
        this.faixaId = faixaId;
        this.nome = nome;
        this.duracao = duracao;
        this.ordem = ordem;
    }

    public Long getFaixaId() {
        return faixaId;
    }

    public void setFaixaId(Long faixaId) {
        this.faixaId = faixaId;
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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
