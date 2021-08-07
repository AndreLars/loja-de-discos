package br.gov.sp.fatec.lojadediscos.entity;

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
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "aar_album_artista",
            joinColumns = @JoinColumn(name = "alb_id"),
            inverseJoinColumns = @JoinColumn(name = "art_id"))
    private Set<Artista> artistas = new HashSet<>();

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
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
}
