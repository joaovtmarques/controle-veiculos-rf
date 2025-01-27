package com.informatica.controle_veiculos.domain.usecase.stamp_file;

import com.informatica.controle_veiculos.data.dto.stamp_file.CreateStampFileRequestDTO;

public interface CreateStampFileUseCase {

  void execute(CreateStampFileRequestDTO stampFileRequestDTO);

}
