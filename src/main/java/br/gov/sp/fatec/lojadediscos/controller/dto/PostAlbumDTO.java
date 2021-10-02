package br.gov.sp.fatec.lojadediscos.controller.dto;

import java.util.List;

public class PostAlbumDTO {
    private String nomeAlbum;
    private Integer anoAlbum;
    private List<String> nomesArtistas;
    private List<PostFaixaDTO> listaFaixas;

    public PostAlbumDTO() {
    }

    public PostAlbumDTO(String nomeAlbum, Integer anoAlbum, List<String> nomesArtistas, List<PostFaixaDTO> listaFaixas) {
        this.nomeAlbum = nomeAlbum;
        this.anoAlbum = anoAlbum;
        this.nomesArtistas = nomesArtistas;
        this.listaFaixas = listaFaixas;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public Integer getAnoAlbum() {
        return anoAlbum;
    }

    public void setAnoAlbum(Integer anoAlbum) {
        this.anoAlbum = anoAlbum;
    }

    public List<String> getNomesArtistas() {
        return nomesArtistas;
    }

    public void setNomesArtistas(List<String> nomesArtistas) {
        this.nomesArtistas = nomesArtistas;
    }

    public List<PostFaixaDTO> getListaFaixas() {
        return listaFaixas;
    }

    public void setListaFaixas(List<PostFaixaDTO> listaFaixas) {
        this.listaFaixas = listaFaixas;
    }
}
