package com.fiap.reserva_equipamentos.api.service;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.dto.EquipamentoRequestDTO;
import com.fiap.reserva_equipamentos.api.repository.EquipamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repo;

    public EquipamentoService(EquipamentoRepository repo) { this.repo = repo; }

    public List<Equipamento> listarAtivos() { return repo.findByAtivoTrue(); }

    public List<Equipamento> buscar(String termo) { return repo.findByDescricaoContainingIgnoreCase(termo); }

    public Equipamento buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipamento não encontrado: " + id));
    }

    public Equipamento criar(EquipamentoRequestDTO dto) {
        var eq = new Equipamento();
        eq.setDescricao(dto.descricao());
        if (dto.ativo() != null) eq.setAtivo(dto.ativo());
        return repo.save(eq);
    }

    public Equipamento atualizar(Long id, EquipamentoRequestDTO dto) {
        var eq = buscarPorId(id);
        eq.setDescricao(dto.descricao());
        if (dto.ativo() != null) eq.setAtivo(dto.ativo());
        return repo.save(eq);
    }

    public void deletar(Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado: " + id);
        repo.deleteById(id);
    }
}
