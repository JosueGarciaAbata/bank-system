package com.josue.banksystem.infraestructure.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.josue.banksystem.infraestructure.security.jwt.TokenJwtConfig.*;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        UserEntity userEntity = null;
        String email = null;
        String password = null;

        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            email = userEntity.getEmail();
            password = userEntity.getPassword(); // Password en crudo.
        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong..." + ex.getMessage());
        }

        // Se llama token porque transporta informacion de seguridad a traves del flujo de autenticacion.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password); // Intento de authenticacion = authenticated = false
        log.info("Attempting authentication for {}", email);

        // Internamente utiliza algun provider para poder realizar la autenticacion utilizando el UserDetailService y el objeto de auteticacion (UsernamePasswordAuthenticationToekn)
        return super.getAuthenticationManager().authenticate(authenticationToken);
    }

    // Entonces se genera el token.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal(); // authResult = authenticated = true
        String email = user.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(authorities))
                .build();

        String jwt = Jwts.builder()
                .subject(email)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + jwt);
        response.setContentType(CONTENT_TYPE);

        Map<String, String> body = new HashMap<>();
        body.put("token", jwt);
        body.put("email", email);
        body.put("message", String.format("You have logged in sucessfuly with %s", email));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
    }

    // Caso contrario se devuelve un mensaje de error.
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Email or password invalid");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(CONTENT_TYPE);
    }
}
