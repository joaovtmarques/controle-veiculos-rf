package com.informatica.controle_veiculos.presentation.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.user.GetUsersRequestDTO;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.usecase.user.GetUsersUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class GetUsersController implements ControllerProtocol<GetUsersRequestDTO, ResponseEntity<Page<User>>> {

  @Autowired
  private GetUsersUseCase getUsers;

  @Override
  @GetMapping
  public ResponseEntity<Page<User>> handle(@RequestBody @Valid GetUsersRequestDTO getUsersDTO) {
    Pageable pageable = PageRequest.of(getUsersDTO.page(), getUsersDTO.size());
    return ResponseEntity.ok(getUsers.execute(pageable));
  }

}
