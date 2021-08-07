package br.gov.sp.fatec.lojadediscos.repository;

import br.gov.sp.fatec.lojadediscos.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByNome(String nome);

    Optional<List<Album>> findAllByArtistasNome(String nome);
}
