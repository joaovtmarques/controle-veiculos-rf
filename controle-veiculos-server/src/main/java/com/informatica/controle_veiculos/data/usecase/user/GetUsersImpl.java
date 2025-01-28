package com.informatica.controle_veiculos.data.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.usecase.user.GetUsersUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;

@Service
public class GetUsersImpl implements GetUsersUseCase {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Page<User> execute(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

}
