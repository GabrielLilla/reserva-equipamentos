package com.fiap.reserva_equipamentos.api.controller;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.dto.EquipamentoRequestDTO;
import com.fiap.reserva_equipamentos.api.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {
    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) { this.service = service; }

    @GetMapping("/ativos")
    public List<Equipamento> ativos() { return service.listarAtivos(); }

    @GetMapping
    public List<Equipamento> buscar(@RequestParam(required = false, defaultValue = "") String q) {
        if (q.isBlank()) return service.listarAtivos();
        return service.buscar(q);
    }

    @GetMapping("/{id}")
    public Equipamento buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamento criar(@RequestBody @Valid EquipamentoRequestDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id, @RequestBody @Valid EquipamentoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
