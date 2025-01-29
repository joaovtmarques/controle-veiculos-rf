package com.informatica.controle_veiculos.data.usecase.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.usecase.user.GetUserStampsUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;

@Service
public class GetUserStampsImpl implements GetUserStampsUseCase {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<Stamp> execute(Long userId) {
    Optional<User> userExists = userRepository.findById(userId);

    if (userExists.isEmpty()) {
      throw new NotFoundException("O dono do selo informado não foi encontrado.");
    }

    return userExists.get().getStamps();
  }

}
