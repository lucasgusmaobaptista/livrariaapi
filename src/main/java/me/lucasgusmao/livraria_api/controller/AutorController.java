package me.lucasgusmao.livraria_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.controller.dto.AutorDTO;
import me.lucasgusmao.livraria_api.controller.dto.ErroResposta;
import me.lucasgusmao.livraria_api.exceptions.OperacaoNaoPermitidaException;
import me.lucasgusmao.livraria_api.exceptions.RegistroDuplicadoException;
import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<Object> salvarAutor(@RequestBody @Valid AutorDTO autor) {
        try {
            Autor autorEntidade = autor.mapearParaAutor();
            autorService.salvar(autorEntidade);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch(RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
        if(autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(),
                    autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        }
        return ResponseEntity.notFound().build();

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAutor(@PathVariable String id) {
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            autorService.deletar(autorOptional.get());

            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> lista = autorService.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> listaDTO = lista.stream()
                .map(autor -> new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(
            @PathVariable String id,
            @RequestBody @Valid AutorDTO dto
    ) {
        try {
            UUID idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setDataNascimento(dto.dataNascimento());
            autor.setNacionalidade(dto.nacionalidade());
            autorService.atualizar(autor);

            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
