package com.barbados.service;

import com.barbados.exception.UserAlreadyExistsException;
import com.barbados.model.Utente;
import com.barbados.model.Role;
import com.barbados.repository.RoleRepository;
import com.barbados.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Utente registerUser(Utente user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());

        // old Implementation..give error if WserRole is Not Present.
       /*Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));*/

        roleRepository.findByName("ROLE_USER")
                .ifPresentOrElse(
                        userRole -> user.setRoles(Collections.singletonList(userRole)),
                        () -> {
                            // Role not found, create it
                            Role newRole = new Role("ROLE_USER"); //a new user has ROLE_USER as default role
                            roleRepository.save(newRole);
                            user.setRoles(Collections.singletonList(newRole));
                        }
                );
        return userRepository.save(user);
    }

    @Override
    public List<Utente> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        Utente theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public Utente getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente not found"));
    }
}