package me.lucasgusmao.livraria_api.controller;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.controller.dto.ClientDTO;
import me.lucasgusmao.livraria_api.controller.mappers.ClientMapper;
import me.lucasgusmao.livraria_api.model.Client;
import me.lucasgusmao.livraria_api.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> criar(@RequestBody ClientDTO dto) {
        Client client = mapper.toEntity(dto);
        service.salvar(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
