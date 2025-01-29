package com.informatica.controle_veiculos.presentation.controller.stamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.stamp.GetStampsRequestDTO;
import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.usecase.stamp.GetStampsUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/stamps")
public class GetStampsController implements ControllerProtocol<GetStampsRequestDTO, ResponseEntity<List<Stamp>>> {

  @Autowired
  private GetStampsUseCase getStamps;

  @Override
  @GetMapping
  public ResponseEntity<List<Stamp>> handle(@RequestBody @Valid GetStampsRequestDTO getStampsDTO) {
    Pageable pageable = PageRequest.of(getStampsDTO.page(), getStampsDTO.size());
    return ResponseEntity.ok(getStamps.execute(pageable));
  }

}
