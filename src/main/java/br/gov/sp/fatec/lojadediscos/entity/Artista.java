package br.gov.sp.fatec.lojadediscos.entity;

import br.gov.sp.fatec.lojadediscos.controller.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "art_artista")
public class Artista {

    @Id
    @Column(name = "art_id")
    @JsonView(View.AlbumCompleto.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistaId;

    @Column(name = "art_nome")
    @JsonView(View.AlbumCompleto.class)
    private String nome;

    @ManyToMany(mappedBy = "artistas", fetch = FetchType.LAZY)
    private Set<Album> albums = new HashSet<>();

    public Long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Long artistaId) {
        this.artistaId = artistaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }
}
