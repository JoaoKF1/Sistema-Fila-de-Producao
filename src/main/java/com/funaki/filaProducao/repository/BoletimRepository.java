package com.funaki.filaProducao.repository;

import com.funaki.filaProducao.model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BoletimRepository extends JpaRepository<Boletim, UUID> {

    Optional<Boletim> findByNumBoletim(String numBoletim);

}
