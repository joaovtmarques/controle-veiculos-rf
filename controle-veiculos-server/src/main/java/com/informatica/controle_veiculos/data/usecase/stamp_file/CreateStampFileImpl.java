package com.informatica.controle_veiculos.data.usecase.stamp_file;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.informatica.controle_veiculos.data.dto.stamp_file.CreateStampFileRequestDTO;
import com.informatica.controle_veiculos.data.exception.BadRequestException;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.stamp_file.CreateStampFileUseCase;
import com.informatica.controle_veiculos.infra.repository.StampRepository;
import com.informatica.controle_veiculos.infra.repository.UserRepository;
import com.informatica.controle_veiculos.infra.repository.VehicleRepository;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

@Component
public class CreateStampFileImpl implements CreateStampFileUseCase {

  @Autowired
  private StampRepository stampRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void execute(CreateStampFileRequestDTO createStampFileRequestDTO) {
    Optional<User> user = userRepository.findById(createStampFileRequestDTO.userId());
    Optional<Vehicle> vehicle = vehicleRepository.findById(createStampFileRequestDTO.vehicleId());
    Optional<Stamp> stamp = stampRepository.findById(createStampFileRequestDTO.stampId());

    if (user.isEmpty() || vehicle.isEmpty() || stamp.isEmpty()) {
      throw new NotFoundException("O selo, o veículo ou o dono do selo não foram encontrados.");
    }

    String company = getCompany(user.get().getCompany());
    String type = vehicle.get().getType();

    Document document = null;

    if (user.get().getCompany().equals("6BIAMV") && type.equals("car")) {
      document = new Document("src/main/resources/stamps/car/selo_carro_6bi_2025.docx");
    } else if (user.get().getCompany().equals("6BIAMV") && type.equals("motorcycle")) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_moto_6bi_2025.docx");
    } else if (user.get().getCompany().equals("12PELPE") && type.equals("car")) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_carro_12pelpe_2025.docx");
    } else if (user.get().getCompany().equals("12PELPE") && type.equals("motorcycle")) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_moto_12pelpe_2025.docx");
    } else if (user.get().getCompany().equals("12CIACOM") && type.equals("car")) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_carro_12ciacom_2025.docx");
    } else if (user.get().getCompany().equals("12CIACOM") && type.equals("motorcycle")) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_moto_12ciacom_2025.docx");
    } else if ((user.get().getCompany().equals("CIACMDO")
        || (user.get().getCompany().equals("12BDAAMV")) && type.equals("car"))) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_carro_12bdaamv_2025.docx");
    } else if ((user.get().getCompany().equals("CIACMDO")
        || (user.get().getCompany().equals("12BDAAMV")) && type.equals("motorcycle"))) {
      document = new Document("src/main/resources/stamps/motorcycle/selo_moto_12bdaamv_2025.docx");
    }

    if (document == null) {
      throw new BadRequestException("Documento para o selo não encontrado");
    }

    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    String expiration = stamp.get().getExpiration().format(formatters);

    document.replace("{rank}", user.get().getRank(), false, true);
    document.replace("{warName}", user.get().getWarName(), false, true);
    document.replace("{company}", company, false, true);
    document.replace("{number}", stamp.get().getNumber().toString(), false, true);
    document.replace("{expiration}", expiration.toString(), false, true);
    document.replace("{plate}", vehicle.get().getPlate(), false, true);

    String finalFilePath = "";
    File dir = new File("tmp/stamps/" + user.get().getRank() + " " + user.get().getWarName());
    if (!dir.exists())
      dir.mkdirs();

    finalFilePath = dir + "/" + user.get().getRank() + user.get().getWarName() + stamp.get().getNumber() + ".pdf";
    document.saveToFile(finalFilePath, FileFormat.PDF);

    document.dispose();
  }

  private String getCompany(String company) {
    switch (company) {
      case "6BIAMV":
        return "6º BI Amv";
      case "12PELPE":
        return "12º Pel PE Amv";
      case "12CIACOM":
        return "12º Cia Com Amv";
      case "CIACMDO":
        return "Cia Cmdo 12º Bda Amv";
      case "12BDA":
        return "12º Bda Amv";
      default:
        return "";
    }
  }

}
