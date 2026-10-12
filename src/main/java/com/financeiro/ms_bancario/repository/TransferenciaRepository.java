package com.financeiro.ms_bancario.repository;

import com.financeiro.ms_bancario.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAllByOrderByDataAgendamentoDesc();
}
