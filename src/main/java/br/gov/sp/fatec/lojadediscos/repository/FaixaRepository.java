package br.gov.sp.fatec.lojadediscos.repository;

import br.gov.sp.fatec.lojadediscos.entity.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaixaRepository extends JpaRepository<Faixa, Long> {
    @PreAuthorize("isAuthenticated()")
    Optional<Faixa> findByNomeAndAlbumNome(String faixaNome, String albumNome);
}

