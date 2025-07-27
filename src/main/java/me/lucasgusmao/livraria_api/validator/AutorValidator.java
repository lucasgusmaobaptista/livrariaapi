package me.lucasgusmao.livraria_api.validator;

import me.lucasgusmao.livraria_api.exceptions.RegistroDuplicadoException;
import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private final AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {
        if(existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );
        if(autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autorEncontrado.get().getId().equals(autor.getId()) && autorEncontrado.isPresent();
    }
}
