package me.lucasgusmao.livraria_api.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "Campo obrigatório!")
        String login,
        @NotBlank(message = "Campo obrigatório!")
        String password,
        @NotBlank(message = "Campo obrigatório!")
        @Email(message = "Email inválido!")
        String email,
        List<String> roles) {
}
