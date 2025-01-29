package com.informatica.controle_veiculos.infra.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_veiculos.domain.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

  Optional<Vehicle> findByPlate(String plate);

  @SuppressWarnings("null")
  Page<Vehicle> findAll(Pageable pageable);

}
