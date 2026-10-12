package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;

import java.math.BigDecimal;

public abstract class FaixaDiasTaxaStrategy implements TaxaStrategy {

    private final int diaInicial;
    private final int diaFinal;
    private final TaxaTransferencia taxa;

    protected FaixaDiasTaxaStrategy(int diaInicial, int diaFinal, TaxaTransferencia taxa) {
        this.diaInicial = diaInicial;
        this.diaFinal = diaFinal;
        this.taxa = taxa;
    }

    @Override
    public boolean aplicaPara(int dia) {
        return dia >= diaInicial && dia <= diaFinal;
    }

    @Override
    public BigDecimal getTaxa() {
        return taxa.getValor();
    }
}
