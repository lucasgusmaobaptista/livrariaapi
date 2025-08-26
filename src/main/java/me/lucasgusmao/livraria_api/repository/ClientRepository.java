package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
