package com.informatica.controle_veiculos.domain.usecase.admin;

import com.informatica.controle_veiculos.data.dto.admin.CreateAdminRequestDTO;
import com.informatica.controle_veiculos.domain.model.Admin;

public interface CreateAdminUseCase {

  Admin execute(CreateAdminRequestDTO adminRequestDTO);

}
