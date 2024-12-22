package com.informatica.controle_veiculos.data.dto.admin;

import java.util.List;

import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.model.Role;

import jakarta.validation.constraints.NotBlank;

public record CreateAdminRequestDTO(
    @NotBlank(message = "O nome do Administrador deve ser informado") String name,
    @NotBlank(message = "O posto/graduação do Administrador deve ser informado") String rank,
    @NotBlank(message = "O nome de guerra do Administrador deve ser informado") String warName,
    @NotBlank(message = "O e-mail do Administrador deve ser informado") String email,
    @NotBlank(message = "A senha do Administrador deve ser informado") String password,
    List<Role> roles) {

  public Admin toModel(String encodedPassword) {
    Admin admin = new Admin();
    admin.setName(name);
    admin.setRank(rank);
    admin.setWarName(warName);
    admin.setEmail(email);
    admin.setPassword(encodedPassword);
    admin.setRoles(roles);
    return admin;
  }
}
