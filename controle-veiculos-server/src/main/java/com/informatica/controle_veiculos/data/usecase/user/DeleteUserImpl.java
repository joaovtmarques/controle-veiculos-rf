package com.informatica.controle_veiculos.data.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.usecase.user.DeleteUserUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;

@Service
public class DeleteUserImpl implements DeleteUserUseCase {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void execute(Long userId) {
    if (userRepository.findById(userId).isEmpty()) {
      throw new NotFoundException("O usuário informado não foi encontrado.");
    }

    userRepository.deleteById(userId);
  }

}
