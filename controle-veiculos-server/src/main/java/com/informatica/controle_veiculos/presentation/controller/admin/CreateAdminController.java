package com.informatica.controle_veiculos.presentation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.admin.CreateAdminRequestDTO;
import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.usecase.admin.CreateAdminUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/admins")
public class CreateAdminController implements ControllerProtocol<CreateAdminRequestDTO, ResponseEntity<Admin>> {

  @Autowired
  private CreateAdminUseCase createAdmin;

  @Override
  @PostMapping
  public ResponseEntity<Admin> handle(@RequestBody @Valid CreateAdminRequestDTO adminRequestDTO) {
    return ResponseEntity.ok(createAdmin.execute(adminRequestDTO));
  }

}
