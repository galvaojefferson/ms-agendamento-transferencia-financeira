package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDe21a30DiasStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDe21a30DiasStrategy() {
        super(21, 30, TaxaTransferencia.TAXA_6_9);
    }
}
