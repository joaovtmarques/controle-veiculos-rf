package com.informatica.controle_veiculos.data.usecase.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.user.GetUserVehiclesUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;

@Service
public class GetUserVehiclesImpl implements GetUserVehiclesUseCase {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<Vehicle> execute(Long userId) {
    Optional<User> user = userRepository.findById(userId);

    if (user.isEmpty()) {
      throw new NotFoundException("O dono do veículo informado não existe.");
    }

    return user.get().getVehicles();

  }

}
