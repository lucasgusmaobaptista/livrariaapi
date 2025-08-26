package me.lucasgusmao.livraria_api.controller.mappers;

import me.lucasgusmao.livraria_api.controller.dto.ClientDTO;
import me.lucasgusmao.livraria_api.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO clientDTO);

    ClientDTO toDTO(Client client);
}
