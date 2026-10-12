package com.financeiro.ms_bancario.controller;

import com.financeiro.ms_bancario.dto.ExtratoTransferenciaDTO;
import com.financeiro.ms_bancario.dto.RealizarTransferenciaDTO;
import com.financeiro.ms_bancario.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencia")
@CrossOrigin
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<RealizarTransferenciaDTO> agendar(
            @RequestBody RealizarTransferenciaDTO transferenciaDTO) {
        transferenciaService.agendar(transferenciaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/extrato")
    public ResponseEntity<List<ExtratoTransferenciaDTO>> consultarExtrato() {
        return ResponseEntity.ok(transferenciaService.consultarExtrato());
    }
}
