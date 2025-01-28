package com.informatica.controle_veiculos.presentation.controller.doc;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.doc.UploadDocRequestDTO;
import com.informatica.controle_veiculos.domain.usecase.doc.UploadDocUseCase;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/docs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class UploadDocController {

  @Autowired
  private UploadDocUseCase uploadDoc;

  @PostMapping
  public ResponseEntity<String> handle(@RequestBody @Valid @ModelAttribute UploadDocRequestDTO uploadDocDTO)
      throws IOException {
    return ResponseEntity.ok(uploadDoc.execute(uploadDocDTO));
  }

}
