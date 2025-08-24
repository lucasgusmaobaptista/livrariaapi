package me.lucasgusmao.livraria_api.service;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.exceptions.OperacaoNaoPermitidaException;
import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.model.Usuario;
import me.lucasgusmao.livraria_api.repository.AutorRepository;
import me.lucasgusmao.livraria_api.repository.LivroRepository;
import me.lucasgusmao.livraria_api.security.SecurityService;
import me.lucasgusmao.livraria_api.validator.AutorValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;
    private final SecurityService securityService;

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        Usuario usuario = securityService.obterUSuarioLogado();
        autor.setUsuario(usuario);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null) {
            throw new IllegalArgumentException("É necessário informar um ID válido para atualização.");
        }
        autorValidator.validar(autor);
        Usuario usuario = securityService.obterUSuarioLogado();
        autor.setUsuario(usuario);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é possível excluir o autor, pois ele possui livros associados.");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if(nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        } else if (nome != null) {
            return repository.findByNome(nome);
        } else if (nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        } else {
            return repository.findAll();
        }
    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor, matcher);

        return repository.findAll(autorExample);
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
