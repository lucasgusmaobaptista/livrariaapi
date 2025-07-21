package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTeste() {
        Autor autor = new Autor();
        autor.setNome("Autor Teste");
        autor.setDataNascimento(LocalDate.of(1990, 1, 1));
        autor.setNacionalidade("Brasileiro");

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTeste() {
        var id = UUID.fromString("641abd05-ab8b-4409-9746-a6c6a39ff00e");

        Optional<Autor> autor = autorRepository.findById(id);

        if(autor.isPresent()) {

            Autor autorEncontrado = autor.get();
            System.out.println("Autor encontrado: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Autor Atualizado");

            autorRepository.save(autorEncontrado);
        }
    }
}
