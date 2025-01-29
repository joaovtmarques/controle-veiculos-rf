package com.informatica.controle_veiculos.presentation.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.usecase.user.GetUserStampsUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users/stamps")
public class GetUserStampsController implements ControllerProtocol<Long, ResponseEntity<List<Stamp>>> {

  @Autowired
  private GetUserStampsUseCase getUserStamps;

  @Override
  @GetMapping("/{userId}")
  public ResponseEntity<List<Stamp>> handle(@PathVariable Long userId) {
    return ResponseEntity.ok(getUserStamps.execute(userId));
  }

}
