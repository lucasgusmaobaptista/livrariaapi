package me.lucasgusmao.livraria_api.security;

import lombok.RequiredArgsConstructor;
import me.lucasgusmao.livraria_api.model.Usuario;
import me.lucasgusmao.livraria_api.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}
