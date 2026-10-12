package com.financeiro.ms_bancario.strategy;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxaStrategyResolver {

    private final List<TaxaStrategy> strategies;

    public TaxaStrategyResolver(List<TaxaStrategy> strategies) {
        this.strategies = strategies;
    }

    public TaxaStrategy resolver(int dia) {
        return strategies.stream()
                .filter(s -> s.aplicaPara(dia))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("dataTransferencia inválida: " + dia));
    }
}
