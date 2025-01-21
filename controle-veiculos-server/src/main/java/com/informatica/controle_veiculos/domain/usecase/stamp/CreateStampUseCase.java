package com.informatica.controle_veiculos.domain.usecase.stamp;

import com.informatica.controle_veiculos.data.dto.stamp.CreateStampRequestDTO;
import com.informatica.controle_veiculos.domain.model.Stamp;

public interface CreateStampUseCase {

  Stamp execute(CreateStampRequestDTO stampDTO);

}
