package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domian.auth.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final UsuarioRepository usuarioRespository;

    public AutenticacionService(UsuarioRepository usuarioRespository) {
        this.usuarioRespository = usuarioRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRespository.findByUsername(username);
    }
}
