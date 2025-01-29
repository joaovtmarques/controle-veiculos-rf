package com.informatica.controle_veiculos.data.dto.stamp;

import jakarta.validation.constraints.NotNull;

public record GetStampsRequestDTO(
    @NotNull(message = "A página deve ser informada.") Integer page,
    @NotNull(message = "A quantidade de selos por página deve ser informada.") Integer size) {

}
