package com.informatica.controle_veiculos.data.usecase.stamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.usecase.stamp.GetStampsUseCase;
import com.informatica.controle_veiculos.infra.repository.StampRepository;

@Service
public class GetStampsImpl implements GetStampsUseCase {

  @Autowired
  private StampRepository stampRepository;

  @Override
  public List<Stamp> execute(Pageable pageable) {
    return stampRepository.findAll(pageable).getContent();
  }

}
