package br.gov.sp.fatec.lojadediscos;

import br.gov.sp.fatec.lojadediscos.entity.Album;
import br.gov.sp.fatec.lojadediscos.entity.Artista;
import br.gov.sp.fatec.lojadediscos.entity.Faixa;
import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.repository.ArtistaRepository;
import br.gov.sp.fatec.lojadediscos.repository.FaixaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
class LojaDeDiscosApplicationTests {

    @Autowired private AlbumRepository albumRepository;

    @Autowired private ArtistaRepository artistaRepository;

    @Autowired private FaixaRepository faixaRepository;

    @Test
    void deve_inserir_novo_artista() {
        var artista = new Artista();
        artista.setNome("Artista1");
        var savedArtista = artistaRepository.save(artista);
        assertNotNull(savedArtista);
        assertEquals(artista.getNome(), savedArtista.getNome());
    }

    @Test
    void deve_inserir_novo_album() {
        final var album = new Album();
        album.setNome("Album1");
        album.setAno(2020);
        final var savedAlbum = albumRepository.save(album);
        assertNotNull(savedAlbum);
        assertEquals(album.getNome(), savedAlbum.getNome());
    }

    @Test
    void deve_inserir_nova_faixa() {
        final var faixa = new Faixa();
        faixa.setDuracao(180);
        faixa.setNome("Faixa1");
        faixa.setOrdem(1);
        final var album = new Album();
        album.setNome("Album1");
        album.setAno(2020);
        album.addFaixa(faixa);
        albumRepository.save(album);
        final var expectedAlbum = albumRepository.findByNome("Album1").orElseThrow();
        assertTrue(expectedAlbum.getFaixas().contains(faixa));
        assertFalse(faixaRepository.findAll().isEmpty());
    }

    @Test
    void deve_encontrar_faixa_por_nome_e_nome_do_album() {
        final var faixa = new Faixa();
        faixa.setDuracao(180);
        faixa.setNome("Faixa1");
        faixa.setOrdem(1);
        final var album = new Album();
        album.setNome("Album1");
        album.setAno(2020);
        album.addFaixa(faixa);
        albumRepository.save(album);
        final var expected = faixaRepository.findByNomeAndAlbumNome("Faixa1", "Album1").orElseThrow();
        assertNotNull(expected);
        assertEquals(expected.getAlbum().getNome(), album.getNome());
        assertEquals(expected.getNome(), faixa.getNome());
    }
}
