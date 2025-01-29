package com.informatica.controle_veiculos.infra.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.model.Vehicle;

public interface StampRepository extends JpaRepository<Stamp, Long> {

  Stamp findTopByOrderByIdDesc();

  Optional<Stamp> findByVehicle(Vehicle vehicle);

  @SuppressWarnings("null")
  Page<Stamp> findAll(Pageable pageable);

}
