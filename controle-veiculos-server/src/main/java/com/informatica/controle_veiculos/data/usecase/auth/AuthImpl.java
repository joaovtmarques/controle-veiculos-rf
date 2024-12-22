package com.informatica.controle_veiculos.data.usecase.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_veiculos.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.data.exception.UnauthorizedException;
import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.usecase.auth.AuthUseCase;
import com.informatica.controle_veiculos.domain.usecase.token.TokenUseCase;
import com.informatica.controle_veiculos.infra.repository.AdminRepository;

@Service
public class AuthImpl implements AuthUseCase {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenUseCase tokenService;

  @Override
  public AuthResponseDTO execute(AuthRequestDTO authRequestDTO) {
    Optional<Admin> adminExists = adminRepository.findByEmail(authRequestDTO.email());

    if (adminExists.isEmpty()) {
      throw new NotFoundException("O administrador com o email: " + authRequestDTO.email() + " não existe");
    }

    if (!passwordEncoder.matches(authRequestDTO.password(), adminExists.get().getPassword())) {
      throw new UnauthorizedException("As credenciais de login não são válidas");
    }

    return tokenService.generateToken(adminExists.get());
  }

}
