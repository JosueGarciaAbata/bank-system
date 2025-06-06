package com.josue.banksystem.infraestructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Objeto que permite verificar en una autenticacion si las credenciales son correctas (ya que este objeto trae datos de la bd).
@AllArgsConstructor
@Service // Le decimos que use mi implementacion para cargar usuarios desde base de datos ya que sino busca en memoria (InMemoryUserDetailsManager).
public class UserDetailsServiceJpa implements UserDetailsService {

        private UserJpaRepository userJpaRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserEntity userEntity = userJpaRepository.findByEmailWithRoles(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User by email was not found."));

                List<GrantedAuthority> authorities = userEntity
                                .getRoles()
                                .stream()
                                .map(role-> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList());

                return new org.springframework.security.core.userdetails.User(
                                userEntity.getEmail(),
                                userEntity.getPassword(),
                                userEntity.isEnabled(),
                                true,
                                true,
                                true,
                                authorities);
        }
}
