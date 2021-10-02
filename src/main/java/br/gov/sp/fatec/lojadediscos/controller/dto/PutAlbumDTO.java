package br.gov.sp.fatec.lojadediscos.controller.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PutAlbumDTO {
    private Long albumId;

    private String nome;

    private Integer ano;

    private Set<String> artistas = new HashSet<>();

    private List<PutFaixaDTO> faixas = new ArrayList<>();

    public PutAlbumDTO() {
    }

    public PutAlbumDTO(Long albumId, String nome, Integer ano, Set<String> artistas, List<PutFaixaDTO> faixas) {
        this.albumId = albumId;
        this.nome = nome;
        this.ano = ano;
        this.artistas = artistas;
        this.faixas = faixas;
    }

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

    public Set<String> getArtistas() {
        return artistas;
    }

    public void setArtistas(Set<String> artistas) {
        this.artistas = artistas;
    }

    public List<PutFaixaDTO> getFaixas() {
        return faixas;
    }

    public void setFaixas(List<PutFaixaDTO> faixas) {
        this.faixas = faixas;
    }
}
