package com.informatica.controle_veiculos.data.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_veiculos.data.dto.user.CreateUserRequestDTO;
import com.informatica.controle_veiculos.data.exception.BadRequestException;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.usecase.user.CreateUserUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;

@Service
public class CreateUserImpl implements CreateUserUseCase {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Override
  public User execute(CreateUserRequestDTO userRequestDTO) {
    try {
      User user = null;

      if (userRepository.findByCpf(userRequestDTO.cpf()).isPresent()) {
        user = userRepository.findByCpf(userRequestDTO.cpf()).get();
      } else {
        user = userRepository.save(userRequestDTO.toModel());
      }

      if (user == null) {
        throw new BadRequestException("Erro ao cadastrar usuário");
      }

      return user;
    } catch (Exception e) {
      throw new BadRequestException("Erro ao cadastrar usuário");
    }
  }

}
