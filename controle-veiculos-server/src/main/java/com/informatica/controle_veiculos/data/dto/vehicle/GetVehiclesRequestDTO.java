package com.informatica.controle_veiculos.data.dto.vehicle;

import jakarta.validation.constraints.NotNull;

public record GetVehiclesRequestDTO(
    @NotNull(message = "A página deve ser informada.") Integer page,
    @NotNull(message = "A quantidade de veículos por página deve ser informada.") Integer size) {

}
