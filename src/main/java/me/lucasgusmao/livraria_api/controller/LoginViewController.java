package me.lucasgusmao.livraria_api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginLogin() {
        return "login";
    }

    @GetMapping("/")
    public String paginaHome(Authentication authentication) {
        return "Olá, " + authentication.getName() + "! Seja bem-vindo(a) à Livraria API.";
    }
}
