package com.informatica.controle_veiculos.data.dto.auth;

import com.informatica.controle_veiculos.domain.model.Admin;

public record AuthResponseDTO(
                String token,
                Admin admin) {

}
