package com.barbados.repository;

import com.barbados.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Optional<Utente> findByEmail(String email);
}
