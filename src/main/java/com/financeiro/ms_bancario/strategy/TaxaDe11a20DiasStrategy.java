package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDe11a20DiasStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDe11a20DiasStrategy() {
        super(11, 20, TaxaTransferencia.TAXA_8_2);
    }
}
