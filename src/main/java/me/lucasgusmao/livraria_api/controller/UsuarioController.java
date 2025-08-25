package me.lucasgusmao.livraria_api.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.controller.dto.UsuarioDTO;
import me.lucasgusmao.livraria_api.controller.mappers.UsuarioMapper;
import me.lucasgusmao.livraria_api.model.Usuario;
import me.lucasgusmao.livraria_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid UsuarioDTO dto) {
        var usuario = mapper.toEntity(dto);
        /* if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        } */
        service.salvar(usuario);
        return ResponseEntity.status(201).build();
    }
}
