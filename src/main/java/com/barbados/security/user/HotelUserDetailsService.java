package com.barbados.security.user;

import com.barbados.model.Utente;
import com.barbados.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente not found"));
        return HotelUserDetails.buildUserDetails(user);
    }
}
