package me.lucasgusmao.livraria_api.service;


import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.model.Client;
import me.lucasgusmao.livraria_api.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client salvar(Client client) {
        String clientSecret = encoder.encode(client.getClientSecret());
        client.setClientSecret(clientSecret);
        return repository.save(client);
    }

    public Client obterPorClientId(String clientId) {
        return repository.findByClientId(clientId);
    }


}
