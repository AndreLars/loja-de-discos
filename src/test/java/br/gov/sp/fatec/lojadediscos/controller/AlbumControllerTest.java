package br.gov.sp.fatec.lojadediscos.controller;

import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.service.AlbumService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Rollback
@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Autowired private AlbumService albumService;

    @Autowired private EntityManager entityManager;

    @Autowired private AlbumRepository albumRepository;

    @Autowired private MockMvc mockMvc;

    private static final String SELECT_NEXTVAL_SEQ = "SELECT nextval('alb_album_alb_id_seq')";

    @Test
    void deve_consultar_com_sucesso_get_album_by_id() throws Exception {
        final var nextval =
                (BigInteger) entityManager.createNativeQuery(SELECT_NEXTVAL_SEQ).getSingleResult();

        albumService.novoAlbum(
                "nomeAlbum", 1990, List.of("Artista"), List.of(new PostFaixaDTO("Faixa", 60)));

        final var idString = nextval.longValue() + 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/album/" + idString))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void nao_deve_consultar_com_sucesso() {
        final var nextval =
                (BigInteger) entityManager.createNativeQuery(SELECT_NEXTVAL_SEQ).getSingleResult();
        assertThrows(
                Exception.class,
                () -> mockMvc.perform(MockMvcRequestBuilders.get("/album/" + nextval)).andReturn());
    }

    @Test
    void deve_consultar_com_sucesso_get_album_by_nome() throws Exception {
        albumService.novoAlbum(
                "nomeAlbum", 1990, List.of("Artista"), List.of(new PostFaixaDTO("Faixa", 60)));
        mockMvc.perform(MockMvcRequestBuilders.get("/album").queryParam("nome", "nomeAlbum"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void nao_deve_consultar_com_sucesso_get_album_by_nome() {
        final var nonExistentName = "Album Que Nao Existe";
        assertThrows(
                Exception.class,
                () ->
                        mockMvc.perform(
                                        MockMvcRequestBuilders.get("/album")
                                                .queryParam("nome", nonExistentName))
                                .andReturn());
    }

    @Test
    void deve_deletar_com_sucesso_album_by_id() throws Exception {
        final var nextval =
                (BigInteger) entityManager.createNativeQuery(SELECT_NEXTVAL_SEQ).getSingleResult();
        albumService.novoAlbum(
                "nomeAlbum", 1990, List.of("Artista"), List.of(new PostFaixaDTO("Faixa", 60)));
        final var idString = nextval.longValue() + 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/album/" + idString)).andReturn();

        assertThrows(Exception.class, () -> albumRepository.findById(idString).orElseThrow());
    }

    @Test
    void nao_deve_deletar_album() {
        final var nextval =
                (BigInteger) entityManager.createNativeQuery(SELECT_NEXTVAL_SEQ).getSingleResult();
        assertThrows(
                Exception.class,
                () ->
                        mockMvc.perform(MockMvcRequestBuilders.delete("/album/" + nextval))
                                .andReturn());
    }

    @Test
    void deve_criar_novo_album() throws Exception {
        final var postAlbumDTO =
                new PostAlbumDTO(
                        "nomeAlbum",
                        1990,
                        List.of("Artista"),
                        List.of(new PostFaixaDTO("Faixa", 60)));
        final var gson = new Gson();
        final var json = gson.toJson(postAlbumDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/album")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andReturn();

        final var result = albumRepository.findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void nao_criar_novo_album() {
        final var postAlbumDTO =
                new PostAlbumDTO("nomeAlbum", 1990, List.of("Artista"), new ArrayList<>());
        final var gson = new Gson();
        final var json = gson.toJson(postAlbumDTO);

        assertThrows(
                Exception.class,
                () ->
                        mockMvc.perform(
                                        MockMvcRequestBuilders.post("/album")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(json))
                                .andReturn());
    }

    @Test
    void deve_atualizar_album() throws Exception {
        final var album1 = "Album1";
        final var anoAlbum = 2019;
        final var artista1 = "Artista1";
        final var faixa1 = "Faixa1";
        final var faixaDuracao = 120;

        List<String> listaArtistas = new ArrayList<>();
        listaArtistas.add(artista1);

        List<PostFaixaDTO> listaFaixas = List.of(new PostFaixaDTO(faixa1, faixaDuracao));

        albumService.novoAlbum(album1, anoAlbum, listaArtistas, listaFaixas);
        final var album = albumRepository.findByNome(album1).orElseThrow();
        final var putAlbumDTO = new PutAlbumDTO();
        putAlbumDTO.setAlbumId(album.getAlbumId());
        putAlbumDTO.setAno(album.getAno());
        putAlbumDTO.setNome("Nome Alterado");
        putAlbumDTO.setArtistas(Set.of("ArtistaNovo"));
        putAlbumDTO.setFaixas(
                List.of(
                        new PutFaixaDTO(
                                album.getFaixas().get(0).getFaixaId(), "Nome Novo", 555, 0)));
        final var gson = new Gson();
        final var json = gson.toJson(putAlbumDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/album")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andReturn();

        final var result = albumRepository.findByNome("Nome Alterado").orElseThrow();
        assertEquals("Nome Alterado", result.getNome());
    }

    @Test
    void nao_deve_atualizar_album() {
        final var putAlbumDTO = new PutAlbumDTO();
        putAlbumDTO.setAlbumId(0L);
        putAlbumDTO.setAno(2019);
        putAlbumDTO.setNome("Nome Alterado");

        final var gson = new Gson();
        final var json = gson.toJson(putAlbumDTO);

        assertThrows(
                Exception.class,
                () ->
                        mockMvc.perform(
                                        MockMvcRequestBuilders.put("/album")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(json))
                                .andReturn());
    }
}
