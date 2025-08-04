package me.lucasgusmao.livraria_api.service;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.model.GeneroLivro;
import me.lucasgusmao.livraria_api.model.Livro;
import me.lucasgusmao.livraria_api.repository.LivroRepository;
import me.lucasgusmao.livraria_api.repository.specs.LivroSpecs;
import me.lucasgusmao.livraria_api.validator.LivroValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static me.lucasgusmao.livraria_api.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public Page<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao, Integer pagina, Integer tamanhoPagina) {

        Specification<Livro> specs = (root, query, cb) -> cb.conjunction();

        if(isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("É necessário informar um ID válido para atualização.");
        }

        validator.validar(livro);
        repository.save(livro);
    }
}