package me.lucasgusmao.livraria_api.controller.dto;

public record ClientDTO(String clientId, String clientSecret, String redirectURI, String scope) {
}
