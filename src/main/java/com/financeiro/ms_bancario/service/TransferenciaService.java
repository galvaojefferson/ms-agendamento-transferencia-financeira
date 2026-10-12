package com.financeiro.ms_bancario.service;

import com.financeiro.ms_bancario.dto.ExtratoTransferenciaDTO;
import com.financeiro.ms_bancario.dto.RealizarTransferenciaDTO;
import com.financeiro.ms_bancario.entity.Transferencia;
import com.financeiro.ms_bancario.repository.TransferenciaRepository;
import com.financeiro.ms_bancario.strategy.TaxaStrategyResolver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    private final TaxaStrategyResolver taxaStrategyResolver;

    public TransferenciaService(TransferenciaRepository transferenciaRepository,
                                TaxaStrategyResolver taxaStrategyResolver) {
        this.transferenciaRepository = transferenciaRepository;
        this.taxaStrategyResolver = taxaStrategyResolver;
    }

    public void agendar(RealizarTransferenciaDTO transferenciaDTO) {

        final Integer dia = transferenciaDTO.getDataTransferencia();
        final BigDecimal taxa = taxaStrategyResolver.resolver(dia).getTaxa();

        if (taxa.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Taxa igual a zero não permitida para dataTransferencia: " + dia);
        }

        final BigDecimal valorCalculado = transferenciaDTO.getValorTransferencia().multiply(taxa);

        salvarTransferencia(transferenciaDTO, transferenciaDTO.getValorTransferencia().add(valorCalculado));
    }

    public List<ExtratoTransferenciaDTO> consultarExtrato() {
        return transferenciaRepository
                .findAllByOrderByDataAgendamentoDesc()
                .stream()
                .map(ExtratoTransferenciaDTO::new)
                .collect(Collectors.toList());
    }

    private void salvarTransferencia(RealizarTransferenciaDTO transferenciaDTO, BigDecimal valorCalculado) {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(transferenciaDTO.getContaOrigem());
        transferencia.setContaDestino(transferenciaDTO.getContaDestino());
        transferencia.setValorTransferencia(transferenciaDTO.getValorTransferencia());
        transferencia.setValorCalculado(valorCalculado);
        transferencia.setDataTransferencia(transferenciaDTO.getDataTransferencia());
        transferencia.setDataAgendamento(transferenciaDTO.getDataAgendamento());
        transferenciaRepository.save(transferencia);
    }
}
