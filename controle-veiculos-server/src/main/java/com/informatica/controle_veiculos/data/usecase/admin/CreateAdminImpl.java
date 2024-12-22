package com.informatica.controle_veiculos.data.usecase.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.data.dto.admin.CreateAdminRequestDTO;
import com.informatica.controle_veiculos.data.exception.AlreadyExistsException;
import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.usecase.admin.CreateAdminUseCase;
import com.informatica.controle_veiculos.infra.repository.AdminRepository;

@Service
public class CreateAdminImpl implements CreateAdminUseCase {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  @Qualifier("bCryptPasswordEncoder")
  private PasswordEncoder passwordEncoder;

  @Override
  public Admin execute(CreateAdminRequestDTO adminRequestDTO) {
    if (adminRepository.findByEmail(adminRequestDTO.email()).isPresent()) {
      throw new AlreadyExistsException("O administrador já está cadastrado");
    }

    return adminRepository.save(adminRequestDTO.toModel(passwordEncoder.encode(adminRequestDTO.password())));
  }

}
