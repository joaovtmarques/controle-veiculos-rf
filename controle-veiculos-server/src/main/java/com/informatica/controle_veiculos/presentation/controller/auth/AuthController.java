package com.informatica.controle_veiculos.presentation.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_veiculos.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_veiculos.domain.usecase.auth.AuthUseCase;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  private AuthUseCase auth;

  @PostMapping
  public ResponseEntity<AuthResponseDTO> handle(@RequestBody AuthRequestDTO authRequestDTO) {
    AuthResponseDTO token = auth.execute(authRequestDTO);
    return ResponseEntity.ok(token);
  }

}