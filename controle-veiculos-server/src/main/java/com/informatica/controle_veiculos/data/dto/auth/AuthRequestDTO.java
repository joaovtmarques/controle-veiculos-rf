package com.informatica.controle_veiculos.data.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "O email do administrador deve ser informado.") String email,
        @NotBlank(message = "A senha do administrador deve ser informada.") String password) {

}
