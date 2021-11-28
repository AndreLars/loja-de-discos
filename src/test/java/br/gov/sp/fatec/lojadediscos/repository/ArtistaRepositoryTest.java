package br.gov.sp.fatec.lojadediscos.repository;

import br.gov.sp.fatec.lojadediscos.entity.Artista;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Rollback
class ArtistaRepositoryTest {

    @Autowired private ArtistaRepository artistaRepository;

    @Test
    void deve_inserir_e_consultar_novo_artista_pelo_nome() {
        final var artista = new Artista();
        artista.setNome("Artista1");
        artistaRepository.save(artista);
        final var queryArtista = artistaRepository.findByNome("Artista1").orElseThrow();
        assertEquals(artista.getNome(), queryArtista.getNome());
    }

    @Test
    void nao_deve_inserir_artista_sem_nome() {
        var artista = new Artista();
        assertThrows(DataIntegrityViolationException.class, () -> artistaRepository.save(artista));
    }

    @Test
    void deve_deletar_artista() {
        final var artista = new Artista();
        artista.setNome("ARTISTA_TESTE");
        final var savedArtista = artistaRepository.save(artista);
        artistaRepository.delete(artista);
        assertThrows(Exception.class, () -> artistaRepository.findById(savedArtista.getArtistaId()).orElseThrow());
    }
}
