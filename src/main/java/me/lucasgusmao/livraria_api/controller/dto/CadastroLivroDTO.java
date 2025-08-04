package me.lucasgusmao.livraria_api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import me.lucasgusmao.livraria_api.model.GeneroLivro;
import me.lucasgusmao.livraria_api.model.Livro;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @NotBlank(message = "ISBN é obrigatório")
        @ISBN
        String isbn,
        @NotBlank(message = "Título é obrigatório")
        String titulo,
        @NotNull(message = "Data de publicação é obrigatória")
        @Past(message = "Data de publicação deve ser no passado")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "ID do autor é obrigatório")
        UUID idAutor
) {
}
