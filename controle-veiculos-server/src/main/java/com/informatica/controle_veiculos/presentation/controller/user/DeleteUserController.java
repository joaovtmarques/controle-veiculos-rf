package com.informatica.controle_veiculos.presentation.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.domain.usecase.user.DeleteUserUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class DeleteUserController implements ControllerProtocol<Long, ResponseEntity<?>> {

  @Autowired
  private DeleteUserUseCase deleteUser;

  @Override
  @DeleteMapping("/{userId}")
  public ResponseEntity<?> handle(@PathVariable Long userId) {
    deleteUser.execute(userId);
    return ResponseEntity.ok().build();
  }

}
