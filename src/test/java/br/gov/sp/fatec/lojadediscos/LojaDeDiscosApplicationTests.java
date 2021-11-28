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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class LojaDeDiscosApplicationTests {

    @Autowired private AlbumRepository albumRepository;

    @Autowired private ArtistaRepository artistaRepository;

    @Autowired private FaixaRepository faixaRepository;

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
        final var expected =
                faixaRepository.findByNomeAndAlbumNome("Faixa1", "Album1").orElseThrow();
        assertNotNull(expected);
        assertEquals(expected.getAlbum().getNome(), album.getNome());
        assertEquals(expected.getNome(), faixa.getNome());
    }

    @Test
    void deve_encontrar_artistas_e_albums() {
        final var artista1 = new Artista();
        artista1.setNome("Artista1");
        final var artista2 = new Artista();
        artista2.setNome("Artista2");
        final var artista3 = new Artista();
        artista3.setNome("Artista3");

        artistaRepository.save(artista1);
        artistaRepository.save(artista2);
        artistaRepository.save(artista3);

        final var album1 = new Album();
        album1.setNome("Album1");
        album1.setAno(2020);
        album1.addArtista(artista1);
        album1.addArtista(artista2);

        final var album2 = new Album();
        album2.setNome("Album2");
        album2.setAno(2021);
        album2.addArtista(artista2);
        album2.addArtista(artista3);

        albumRepository.save(album1);
        albumRepository.save(album2);

        final var savedAlbum1 = albumRepository.findByNome("Album1").orElseThrow();
        final var savedAlbum2 = albumRepository.findByNome("Album2").orElseThrow();

        assertTrue(savedAlbum1.getArtistas().contains(artista1));
        assertTrue(savedAlbum1.getArtistas().contains(artista2));
        assertTrue(savedAlbum2.getArtistas().contains(artista2));
        assertTrue(savedAlbum2.getArtistas().contains(artista3));
        assertThrows(
                NoSuchElementException.class, () -> albumRepository.findByNome("Album3").orElseThrow());
    }
}
