package com.informatica.controle_veiculos.presentation.controller.stamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.usecase.stamp.CreateStampUseCase;
import com.informatica.controle_veiculos.data.dto.stamp.CreateStampRequestDTO;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/stamps")
public class CreateStampController implements ControllerProtocol<CreateStampRequestDTO, ResponseEntity<Stamp>> {

  @Autowired
  private CreateStampUseCase createStamp;

  @Override
  @PostMapping
  public ResponseEntity<Stamp> handle(@RequestBody @Valid CreateStampRequestDTO stampDTO) {
    return ResponseEntity.status(201).body(createStamp.execute(stampDTO));
  }

}
