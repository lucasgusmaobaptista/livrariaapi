package me.lucasgusmao.livraria_api.service;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.model.Usuario;
import me.lucasgusmao.livraria_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario usuario) {
        var senha = usuario.getPassword();
        usuario.setPassword(encoder.encode(senha));
        repository.save(usuario);
    }

    public Usuario obterPorLogin(String login) {
        return repository.findByLogin(login);
    }

    public Usuario obterPorEmail(String email) {
        return repository.findByEmail(email);
    }
}
