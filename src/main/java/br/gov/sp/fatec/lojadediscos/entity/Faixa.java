package br.gov.sp.fatec.lojadediscos.entity;

import br.gov.sp.fatec.lojadediscos.controller.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

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
    @JsonView(View.AlbumCompleto.class)
    private Long faixaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alb_id")
    private Album album;

    @Column(name = "fai_ordem")
    @JsonView(View.AlbumCompleto.class)
    private Integer ordem;

    @Column(name = "fai_nome")
    @JsonView(View.AlbumCompleto.class)
    private String nome;

    @Column(name = "fai_duracao")
    @JsonView(View.AlbumCompleto.class)
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
