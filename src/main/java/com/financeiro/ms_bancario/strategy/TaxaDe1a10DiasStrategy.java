package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDe1a10DiasStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDe1a10DiasStrategy() {
        super(1, 10, TaxaTransferencia.TAXA_ZERO);
    }
}
