package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.model.GeneroLivro;
import me.lucasgusmao.livraria_api.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setIsbn("123-4567890123");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setPreco(BigDecimal.valueOf(100));

        Autor autor = autorRepository.findById(UUID.fromString("9689d186-597d-4619-929b-7c2b19c3c2eb"))
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livro.setAutor(autor);

        var livroSalvo = livroRepository.save(livro);
        System.out.println("Livro salvo: " + livroSalvo);
    }

    @Test
    void atualizarAutorTeste() {
        UUID id = UUID.fromString("0f26769f-beb3-4ae1-893f-0800caa9c3f9");
        var livroAtualizar = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        UUID autorId = UUID.fromString("adbd1082-091d-4414-b0f4-0ce0272e0c95");
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livroAtualizar.setAutor(autor);

        livroRepository.save(livroAtualizar);
    }

    @Test
    void deletarTeste() {
        UUID id = UUID.fromString("0f26769f-beb3-4ae1-893f-0800caa9c3f9");
        livroRepository.deleteById(id);
    }

    @Test
    void buscarLivroTeste() {
        UUID id = UUID.fromString("64c61629-12e6-4e6e-be62-7d74b034dcc6");
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        System.out.println("Livro encontrado: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
    }
}