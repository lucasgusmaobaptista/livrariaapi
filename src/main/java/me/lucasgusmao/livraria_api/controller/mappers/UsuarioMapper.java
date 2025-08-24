package me.lucasgusmao.livraria_api.controller.mappers;

import me.lucasgusmao.livraria_api.controller.dto.UsuarioDTO;
import me.lucasgusmao.livraria_api.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    public abstract Usuario toEntity(UsuarioDTO dto);
}
