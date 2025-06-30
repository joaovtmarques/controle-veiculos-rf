package com.informatica.controle_veiculos.data.usecase.doc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.informatica.controle_veiculos.data.dto.doc.UploadDocRequestDTO;
import com.informatica.controle_veiculos.data.exception.BadRequestException;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.doc.UploadDocUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;
import com.informatica.controle_veiculos.infra.repository.VehicleRepository;

@Service
public class UploadDocImpl implements UploadDocUseCase {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public String execute(UploadDocRequestDTO uploadDocDTO) throws IOException {
    Optional<User> userExists = userRepository.findById(uploadDocDTO.userId());
    Optional<Vehicle> vehicleExists = vehicleRepository.findById(uploadDocDTO.vehicleId());

    if (userExists.isEmpty()) {
      throw new NotFoundException("O dono dos documentos não foi encontrado.");
    }

    if (vehicleExists.isEmpty()) {
      throw new NotFoundException("O veículo não foi encontrado.");
    }

    String uploadDir = "tmp/uploads/" + userExists.get().getRank() + userExists.get().getWarName();
    createFoldIfNotExists(uploadDir);

    saveDoc(uploadDocDTO.cnh(), uploadDir, "CNH-" + vehicleExists.get().getId() + ".pdf");
    saveDoc(uploadDocDTO.crlv(), uploadDir, "CRLV-" + vehicleExists.get().getId() + ".pdf");

    if (uploadDocDTO.authorization() != null && !uploadDocDTO.authorization().isEmpty()) {
      saveDoc(uploadDocDTO.authorization(), uploadDir, "AUTORIZACAO-" + vehicleExists.get().getId() + ".pdf");
    }

    return "Os documentos foram enviados com sucesso.";
  }

  private void createFoldIfNotExists(String dir) {
    File folder = new File(dir);
    if (!folder.exists()) {
      folder.mkdirs();
    }
  }

  private void saveDoc(MultipartFile doc, String dir, String fileName) {
    try {
      Path destino = Path.of(dir, fileName);
      Files.copy(doc.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new BadRequestException("Erro ao salvar arquivo: " + fileName);
    }
  }

}
