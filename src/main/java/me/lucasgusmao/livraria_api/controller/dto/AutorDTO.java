package me.lucasgusmao.livraria_api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import me.lucasgusmao.livraria_api.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório!")
        @Size(max = 100, min=2, message = "Campo deve ter entre 2 e 100 caracteres!")
        String nome,
        @NotNull(message = "Campo obrigatório!")
        @Past(message = "Digite uma data de nascimento válida!")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório!")
        @Size(max = 50, message = "Campo deve ter no máximo 50 caracteres!")
        String nacionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
