package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDe41a50DiasStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDe41a50DiasStrategy() {
        super(41, 50, TaxaTransferencia.TAXA_1_7);
    }
}
