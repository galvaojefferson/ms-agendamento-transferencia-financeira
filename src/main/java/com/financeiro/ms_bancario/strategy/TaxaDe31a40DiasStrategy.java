package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDe31a40DiasStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDe31a40DiasStrategy() {
        super(31, 40, TaxaTransferencia.TAXA_4_7);
    }
}
