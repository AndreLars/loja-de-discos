package br.gov.sp.fatec.lojadediscos.service;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface AlbumService {
    void novoAlbum(String nomeAlbum, Integer anoAlbum, List<String> nomesArtistas, List<Pair<String, Integer>> listaFaixas);
}
