package com.fiap.reserva_equipamentos.api.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipamentoRequestDTO(
        @NotBlank(message = "Descrição é obrigatória")
        String descricao,
        Boolean ativo
) {}
