package com.financeiro.ms_bancario.strategy;

import java.math.BigDecimal;

public interface TaxaStrategy {

    boolean aplicaPara(int dia);

    BigDecimal getTaxa();
}
