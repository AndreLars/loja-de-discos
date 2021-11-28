package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.controller.dto.PostFaixaDTO;
import br.gov.sp.fatec.lojadediscos.entity.Album;

import java.util.List;

public interface AlbumService {
    Album novoAlbum(
            String nomeAlbum,
            Integer anoAlbum,
            List<String> nomesArtistas,
            List<PostFaixaDTO> listaFaixas);

    Album findAlbumById(long albumId);

    Album findAlbumByNome(String nome);

    void removeAlbumById(long albumId);

    Album putAlbum(Album album) throws Exception;
}
