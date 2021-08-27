package br.gov.sp.fatec.lojadediscos.controller;

import br.gov.sp.fatec.lojadediscos.entity.Album;
import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.service.AlbumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback
class AlbumControllerTest {

    @Autowired
    private AlbumController albumController;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void deve_retornar_200_get_album_by_id() throws JsonProcessingException {
        final var nextval = (BigInteger) entityManager.createNativeQuery("SELECT nextval('alb_album_alb_id_seq')").getSingleResult();
        albumService.novoAlbum("nomeAlbum", 1990, List.of("Artista"), List.of(Pair.of("Faixa", 60)));
        final var result = albumController.getAlbum(nextval.longValue() + 1L);
        final var expected = "{\"nome\":\"nomeAlbum\",\"ano\":1990,\"artistas\":[{}],\"faixas\":[{}]}";
        assertEquals(expected, result);
    }
}
