package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.controller.PostFaixaDTO;
import br.gov.sp.fatec.lojadediscos.entity.Album;

import java.util.List;

public interface AlbumService {
    void novoAlbum(
            String nomeAlbum,
            Integer anoAlbum,
            List<String> nomesArtistas,
            List<PostFaixaDTO> listaFaixas);

    Album findAlbumById(long albumId);

    Album findAlbumByNome(String nome);

    void removeAlbumById(long albumId);

    void putAlbum(Album album);
}
