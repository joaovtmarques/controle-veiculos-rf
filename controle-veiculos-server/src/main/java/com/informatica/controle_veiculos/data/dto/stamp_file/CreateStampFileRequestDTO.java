package com.informatica.controle_veiculos.data.dto.stamp_file;

import jakarta.validation.constraints.NotNull;

public record CreateStampFileRequestDTO(
    @NotNull(message = "O selo deve ser informado.") Long stampId,
    @NotNull(message = "O veículo deve ser informado.") Long vehicleId,
    @NotNull(message = "O dono do selo deve ser informado.") Long userId) {

}
