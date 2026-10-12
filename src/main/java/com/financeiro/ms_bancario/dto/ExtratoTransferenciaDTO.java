package com.financeiro.ms_bancario.dto;

import com.financeiro.ms_bancario.entity.Transferencia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExtratoTransferenciaDTO {

    private final Long id;
    private final String contaOrigem;
    private final String contaDestino;
    private final BigDecimal valorTransferencia;
    private final BigDecimal valorCalculado;
    private final Integer dataTransferencia;
    private final LocalDate dataAgendamento;

    public ExtratoTransferenciaDTO(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.contaOrigem = transferencia.getContaOrigem();
        this.contaDestino = transferencia.getContaDestino();
        this.valorTransferencia = transferencia.getValorTransferencia();
        this.valorCalculado = transferencia.getValorCalculado();
        this.dataTransferencia = transferencia.getDataTransferencia();
        this.dataAgendamento = transferencia.getDataAgendamento();
    }

    public Long getId() {
        return id;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    public BigDecimal getValorCalculado() {
        return valorCalculado;
    }

    public Integer getDataTransferencia() {
        return dataTransferencia;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }
}
