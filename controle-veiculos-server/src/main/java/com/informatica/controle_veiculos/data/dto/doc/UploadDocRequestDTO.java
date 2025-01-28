package com.informatica.controle_veiculos.data.dto.doc;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public record UploadDocRequestDTO(
                @NotNull(message = "O dono dos documentos deve ser informado.") Long userId,
                @NotNull(message = "O veículo deve ser informado.") Long vehicleId,
                @NotNull(message = "A CNH deve ser enviada.") MultipartFile cnh,
                @NotNull(message = "A CRLV deve ser enviada.") MultipartFile crlv,
                MultipartFile authorization) {

}
