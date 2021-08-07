package br.gov.sp.fatec.lojadediscos.repository;

import br.gov.sp.fatec.lojadediscos.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {}
