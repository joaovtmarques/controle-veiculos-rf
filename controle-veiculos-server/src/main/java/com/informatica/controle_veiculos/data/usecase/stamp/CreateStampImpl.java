package com.informatica.controle_veiculos.data.usecase.stamp;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_veiculos.data.dto.stamp.CreateStampRequestDTO;
import com.informatica.controle_veiculos.data.dto.stamp_file.CreateStampFileRequestDTO;
import com.informatica.controle_veiculos.data.exception.AlreadyExistsException;
import com.informatica.controle_veiculos.data.exception.BadRequestException;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.stamp.CreateStampUseCase;
import com.informatica.controle_veiculos.domain.usecase.stamp_file.CreateStampFileUseCase;
import com.informatica.controle_veiculos.infra.repository.StampRepository;
import com.informatica.controle_veiculos.infra.repository.UserRepository;
import com.informatica.controle_veiculos.infra.repository.VehicleRepository;

@Service
public class CreateStampImpl implements CreateStampUseCase {

  @Autowired
  private StampRepository stampRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  CreateStampFileUseCase createStampFile;

  @Transactional
  @Override
  public Stamp execute(CreateStampRequestDTO stampDTO) {
    Optional<Vehicle> vehicle = vehicleRepository.findById(stampDTO.vehicleId());
    Optional<User> user = userRepository.findById(stampDTO.userId());

    if (vehicle.isEmpty() || user.isEmpty()) {
      throw new NotFoundException("O veículo ou o dono do selo não foram encontrados.");
    }

    if (stampRepository.findByVehicle(vehicle.get()).isPresent()) {
      throw new AlreadyExistsException("O veículo informado ja possui um selo.");
    }

    Stamp lastStamp = stampRepository.findTopByOrderByIdDesc();
    Long number;

    if (lastStamp != null) {
      number = lastStamp.getNumber() + 1;
    } else {
      number = 1L;
    }

    char[] plate = vehicle.get().getPlate().toCharArray();
    char lastCharOfPlate = plate[plate.length - 1];

    int year = vehicle.get().getLicensing();

    if (year < 2024) {
      throw new BadRequestException("O licenciamento do veículo está atrasado.");
    }

    int month = Integer.parseInt(getMonth(vehicle.get().getType(), lastCharOfPlate));

    YearMonth expLicensing = YearMonth.of((year + 1), month);
    LocalDate expiration = expLicensing.atEndOfMonth();

    Stamp stampToSave = stampDTO.toModel(vehicle.get(), user.get(), number, expiration, "U_ANALYSIS");

    Stamp stamp = stampRepository.save(stampToSave);

    createStampFile
        .execute(new CreateStampFileRequestDTO(stamp.getId(), vehicle.get().getId(), user.get().getId()));

    return stamp;
  }

  private String getMonth(String type, char lastCharOfPlate) {
    if (!Character.isDigit(lastCharOfPlate)) {
      throw new BadRequestException("O último caractere da placa deve ser um dígito numérico");
    }

    switch (lastCharOfPlate) {
      case '1':
      case '2':
        return "7";
      case '3':
      case '4':
        return "8";
      case '5':
      case '6':
        return "9";
      case '7':
      case '8':
        return "10";
      case '9':
        return "11";
      case '0':
        return "12";
      default:
        return "";
    }
  }
}
