package com.financeiro.ms_bancario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RealizarTransferenciaDTO {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorTransferencia;
    private Integer dataTransferencia;
    private LocalDate dataAgendamento;

    // Necessário para a desserialização do Jackson
    private RealizarTransferenciaDTO() {
    }

    private RealizarTransferenciaDTO(Builder builder) {
        this.contaOrigem = builder.contaOrigem;
        this.contaDestino = builder.contaDestino;
        this.valorTransferencia = builder.valorTransferencia;
        this.dataTransferencia = builder.dataTransferencia;
        this.dataAgendamento = builder.dataAgendamento;
    }

    public static Builder builder() {
        return new Builder();
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

    public Integer getDataTransferencia() {
        return dataTransferencia;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public static class Builder {

        private String contaOrigem;
        private String contaDestino;
        private BigDecimal valorTransferencia;
        private Integer dataTransferencia;
        private LocalDate dataAgendamento;

        private Builder() {
        }

        public Builder contaOrigem(String contaOrigem) {
            this.contaOrigem = contaOrigem;
            return this;
        }

        public Builder contaDestino(String contaDestino) {
            this.contaDestino = contaDestino;
            return this;
        }

        public Builder valorTransferencia(BigDecimal valorTransferencia) {
            this.valorTransferencia = valorTransferencia;
            return this;
        }

        public Builder dataTransferencia(Integer dataTransferencia) {
            this.dataTransferencia = dataTransferencia;
            return this;
        }

        public Builder dataAgendamento(LocalDate dataAgendamento) {
            this.dataAgendamento = dataAgendamento;
            return this;
        }

        public RealizarTransferenciaDTO build() {
            return new RealizarTransferenciaDTO(this);
        }
    }
}
