package br.gov.sp.fatec.lojadediscos.entity;

import br.gov.sp.fatec.lojadediscos.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "alb_album")
public class Album {

    @Id
    @Column(name = "alb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    @Column(name = "alb_nome")
    @JsonView(Views.Public.class)
    private String nome;

    @Column(name = "alb_ano")
    @JsonView(Views.Public.class)
    private Integer ano;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "aar_album_artista",
            joinColumns = @JoinColumn(name = "alb_id"),
            inverseJoinColumns = @JoinColumn(name = "art_id"))
    @JsonView(Views.Public.class)
    private Set<Artista> artistas = new HashSet<>();

    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonView(Views.Public.class)
    private List<Faixa> faixas = new ArrayList<>();

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Set<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(Set<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<Faixa> getFaixas() {
        return faixas;
    }

    public void setFaixas(List<Faixa> faixas) {
        this.faixas = faixas;
    }

    public void addFaixa(Faixa faixa) {
        faixas.add(faixa);
        faixa.setAlbum(this);
    }

    public void removeFaixa(Faixa faixa) {
        faixas.remove(faixa);
        faixa.setAlbum(null);
    }

    public void addArtista(Artista artista) {
        artistas.add(artista);
        artista.addAlbum(this);
    }

    public void removeArtista(Artista artista) {
        artistas.remove(artista);
        artista.removeAlbum(this);
    }
}
