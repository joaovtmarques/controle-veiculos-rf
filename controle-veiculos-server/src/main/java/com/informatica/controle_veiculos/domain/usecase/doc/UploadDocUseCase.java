package com.informatica.controle_veiculos.domain.usecase.doc;

import java.io.IOException;

import com.informatica.controle_veiculos.data.dto.doc.UploadDocRequestDTO;

public interface UploadDocUseCase {

  String execute(UploadDocRequestDTO uploadDocDTO) throws IOException;

}
