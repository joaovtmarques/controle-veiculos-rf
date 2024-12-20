package com.informatica.controle_veiculos.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_veiculos.domain.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

  Optional<Admin> findByEmail(String email);

}
