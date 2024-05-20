package com.barbados.service;

import com.barbados.model.Utente;

import java.util.List;

public interface IUserService {
    Utente registerUser(Utente user);
    List<Utente> getUsers();
    void deleteUser(String email);
    Utente getUser(String email);
}