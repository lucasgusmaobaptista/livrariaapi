package me.lucasgusmao.livraria_api.controller.mappers;

import me.lucasgusmao.livraria_api.controller.dto.AutorDTO;
import me.lucasgusmao.livraria_api.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toDTO(Autor autor);
}
