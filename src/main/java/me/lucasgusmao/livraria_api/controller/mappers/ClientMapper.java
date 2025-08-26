package me.lucasgusmao.livraria_api.controller.mappers;

import me.lucasgusmao.livraria_api.controller.dto.ClientDTO;
import me.lucasgusmao.livraria_api.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toCDTO(ClientDTO clientDTO);

    ClientDTO toEntity(Client client);
}
