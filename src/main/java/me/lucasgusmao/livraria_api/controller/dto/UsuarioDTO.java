package me.lucasgusmao.livraria_api.controller.dto;

import java.util.List;

public record UsuarioDTO(String login, String password, List<String> roles) {
}
