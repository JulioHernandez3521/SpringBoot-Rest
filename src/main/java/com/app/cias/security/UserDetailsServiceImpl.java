package com.app.cias.security;

import com.app.cias.service.PersonasImplentSerivce;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.app.cias.model.Persona;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonasImplentSerivce personasImplentSerivce;

    public UserDetailsServiceImpl(PersonasImplentSerivce personasImplentSerivce) {
        this.personasImplentSerivce = personasImplentSerivce;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persona personaEncontrada = personasImplentSerivce.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario no esta registrado"));
        return new UserDetailsImpl(personaEncontrada);
    }
}
