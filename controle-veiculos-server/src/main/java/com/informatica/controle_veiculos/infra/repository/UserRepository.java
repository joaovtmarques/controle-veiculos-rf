package com.informatica.controle_veiculos.infra.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_veiculos.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByCpf(String cpf);

  @SuppressWarnings("null")
  Page<User> findAll(Pageable pageable);

}
