package me.lucasgusmao.livraria_api.service;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.exceptions.OperacaoNaoPermitidaException;
import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.repository.AutorRepository;
import me.lucasgusmao.livraria_api.repository.LivroRepository;
import me.lucasgusmao.livraria_api.validator.AutorValidator;
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

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null) {
            throw new IllegalArgumentException("É necessário informar um ID válido para atualização.");
        }
        autorValidator.validar(autor);
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

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
