package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.controller.PostFaixaDTO;
import br.gov.sp.fatec.lojadediscos.controller.PutFaixaDTO;
import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.repository.ArtistaRepository;
import br.gov.sp.fatec.lojadediscos.repository.FaixaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Rollback
class AlbumServiceImplTest {

    @Autowired private AlbumService albumService;

    @Autowired private AlbumRepository albumRepository;

    @Autowired private ArtistaRepository artistaRepository;

    @Autowired private FaixaRepository faixaRepository;

    @Test
    void deve_salvar_novo_album() {
        final var album1 = "Album1";
        final var anoAlbum = 2019;
        final var artista1 = "Artista1";
        final var faixa1 = "Faixa1";
        final var faixaDuracao = 120;

        List<String> listaArtistas = new ArrayList<>();
        listaArtistas.add(artista1);

        List<PostFaixaDTO> listaFaixas = List.of(new PostFaixaDTO(faixa1, faixaDuracao));

        albumService.novoAlbum(album1, anoAlbum, listaArtistas, listaFaixas);
        assertEquals(
                faixaRepository.findByNomeAndAlbumNome(faixa1, album1).orElseThrow().getDuracao(),
                faixaDuracao);
        assertEquals(artistaRepository.findByNome(artista1).orElseThrow().getNome(), artista1);
        assertEquals(albumRepository.findByNome(album1).orElseThrow().getAno(), anoAlbum);

    }

    @Test
    void nao_deve_salvar_novo_album() {
        final var nomeAlbum = "Album1";
        final var anoAlbum = 2019;
        List<String> listaArtistas = new ArrayList<>();
        List<PostFaixaDTO> listaFaixas = new ArrayList<>();
        assertThrows(
                IllegalArgumentException.class,
                () -> albumService.novoAlbum(nomeAlbum, anoAlbum, listaArtistas, listaFaixas));
    }
}
