package com.financeiro.ms_bancario.enums;

import java.math.BigDecimal;

public enum TaxaTransferencia {

    TAXA_2_5(new BigDecimal("0.025")),
    TAXA_ZERO(BigDecimal.ZERO),
    TAXA_8_2(new BigDecimal("0.082")),
    TAXA_6_9(new BigDecimal("0.069")),
    TAXA_4_7(new BigDecimal("0.047")),
    TAXA_1_7(new BigDecimal("0.017"));

    private final BigDecimal valor;

    TaxaTransferencia(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
