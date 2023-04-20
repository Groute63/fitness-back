package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.example.services.ClientService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {
    private final ClientRepository userRepository;

    @Override
    public void register(String clientId, String clientSecret) throws LoginException {
        if (userRepository.findById(clientId).isPresent())
            throw new LoginException("Client with id: " + clientId + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new Client(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) throws LoginException {
        Optional<Client> optionalUserEntity = userRepository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException("Client with id: " + clientId + " not found");

        Client clientEntity = optionalUserEntity.get();
        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
