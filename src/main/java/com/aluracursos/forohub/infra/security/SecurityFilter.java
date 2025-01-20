package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domian.auth.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRespository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRespository) {
        this.tokenService = tokenService;
        this.usuarioRespository = usuarioRespository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");

            var subject = tokenService.getSubject(token);
            if (subject != null) {
                //token valido
                var usuario = usuarioRespository.findByUsername(subject);

                var autentification = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(autentification);
            }
        }

        filterChain.doFilter(request, response);
    }
}
