package com.informatica.controle_veiculos.presentation.controller.custom_error_controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {

    // Mapeia o caminho "/error" para capturar erros e retornar a mensagem
    // personalizada
    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND) // Garantir que o status 404 seja retornado
    @ResponseBody // Retorna uma resposta de texto simples
    public String handleError(HttpServletRequest request) {
        // Retorna a mensagem personalizada para rota não encontrada
        return "Rota não encontrada";
    }
}
