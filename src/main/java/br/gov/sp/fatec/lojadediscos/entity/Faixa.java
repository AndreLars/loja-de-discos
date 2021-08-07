package br.gov.sp.fatec.lojadediscos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fai_faixa")
public class Faixa {

    @Id
    @Column(name = "fai_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faixaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alb_id")
    private Album album;

    @Column(name = "fai_ordem")
    private Integer ordem;

    @Column(name = "fai_nome")
    private String nome;

    @Column(name = "fai_duracao")
    private Integer duracao;

    public Long getFaixaId() {
        return faixaId;
    }

    public void setFaixaId(Long faixaId) {
        this.faixaId = faixaId;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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
