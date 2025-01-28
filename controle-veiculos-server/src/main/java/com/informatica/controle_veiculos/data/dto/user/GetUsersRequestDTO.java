package com.informatica.controle_veiculos.data.dto.user;

import jakarta.validation.constraints.NotNull;

public record GetUsersRequestDTO(
    @NotNull(message = "A página deve ser informada.") Integer page,
    @NotNull(message = "A quantidade de usuários por página deve ser informada.") Integer size) {

}
