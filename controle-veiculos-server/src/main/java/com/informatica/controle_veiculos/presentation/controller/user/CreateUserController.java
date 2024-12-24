package com.informatica.controle_veiculos.presentation.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.user.CreateUserRequestDTO;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.usecase.user.CreateUserUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class CreateUserController implements ControllerProtocol<CreateUserRequestDTO, ResponseEntity<User>> {

  @Autowired
  private CreateUserUseCase createUser;

  @Override
  @PostMapping
  public ResponseEntity<User> handle(@RequestBody @Valid CreateUserRequestDTO userRequestDTO) {
    return ResponseEntity.status(201).body(createUser.execute(userRequestDTO));
  }

}
