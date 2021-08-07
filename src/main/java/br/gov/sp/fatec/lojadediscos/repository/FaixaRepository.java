package br.gov.sp.fatec.lojadediscos.repository;

import br.gov.sp.fatec.lojadediscos.entity.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaixaRepository extends JpaRepository<Faixa, Long> {}
