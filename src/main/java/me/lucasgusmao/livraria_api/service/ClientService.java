package me.lucasgusmao.livraria_api.service;


import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.model.Client;
import me.lucasgusmao.livraria_api.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Client salvar(Client client) {
        return repository.save(client);
    }

    public Client obterPorClientId(String clientId) {
        return repository.findByClientId(clientId);
    }


}
