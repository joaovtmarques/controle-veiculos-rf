package com.informatica.controle_veiculos.data.dto.user;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.informatica.controle_veiculos.domain.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequestDTO(
    @NotBlank(message = "O nome completo do Militar deve ser informado") String name,
    @NotBlank(message = "O posto/graduação do Militar deve ser informado") String rank,
    @NotBlank(message = "O nome de guerra do Militar deve ser informado") String warName,
    @NotBlank(message = "O e-mail do Militar deve ser informado") @Email(message = "O e-mail do Militar deve ser válido") String email,
    @NotBlank(message = "O número de telefone do Militar deve ser informado") String phoneNumber,
    @NotBlank(message = "O cpf do Militar deve ser informado") @CPF(message = "O CPF do Militar deve ser válido") String cpf,
    @NotBlank(message = "A OM/Su do Militar deve ser informado") String company,
    @NotNull(message = "O vencimento da habilitação do Militar deve ser informado") LocalDate driverLicenseExpiration) {
  public User toModel() {
    User user = new User();
    user.setName(name);
    user.setRank(rank);
    user.setWarName(warName);
    user.setEmail(email);
    user.setPhoneNumber(phoneNumber);
    user.setCpf(cpf);
    user.setCompany(company);
    user.setDriverLicenseExpiration(driverLicenseExpiration);
    return user;
  }
}
