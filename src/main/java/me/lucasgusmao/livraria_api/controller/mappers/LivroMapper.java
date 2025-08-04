package me.lucasgusmao.livraria_api.controller.mappers;

import me.lucasgusmao.livraria_api.controller.dto.CadastroLivroDTO;
import me.lucasgusmao.livraria_api.controller.dto.PesquisaLivroDTO;
import me.lucasgusmao.livraria_api.model.Livro;
import me.lucasgusmao.livraria_api.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )" )
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract PesquisaLivroDTO toDTO (Livro livro);

}
