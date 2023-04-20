package org.example.services;

import javax.security.auth.login.LoginException;

public interface ClientService {
    void register(String clientId, String clientSecret) throws LoginException;
    void checkCredentials(String clientId, String clientSecret) throws LoginException;
}
