package com.informatica.controle_veiculos.domain.usecase.stamp;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.informatica.controle_veiculos.domain.model.Stamp;

public interface GetStampsUseCase {

  List<Stamp> execute(Pageable pageable);

}
