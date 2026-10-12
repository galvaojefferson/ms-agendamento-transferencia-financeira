package com.financeiro.ms_bancario.strategy;

import com.financeiro.ms_bancario.enums.TaxaTransferencia;
import org.springframework.stereotype.Component;

@Component
public class TaxaDiaAtualStrategy extends FaixaDiasTaxaStrategy {

    public TaxaDiaAtualStrategy() {
        super(0, 0, TaxaTransferencia.TAXA_2_5);
    }
}
