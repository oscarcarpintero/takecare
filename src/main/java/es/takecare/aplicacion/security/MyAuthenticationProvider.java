package es.takecare.aplicacion.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import es.takecare.aplicacion.model.Paciente;
import es.takecare.aplicacion.repository.PacienteRepository;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        Set<GrantedAuthority> grantedAuths = new HashSet<>();

        if ("admin".equals(user.toLowerCase()) && "takecare".equals(password)) {

            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(
                    user, password, grantedAuths);
            return authenticationContext;

        } else {
            Optional<Paciente> pacienteLogado = pacienteRepository.findByNombreAndPassword(user,
                    password);
            if (pacienteLogado.isPresent()) {

                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

                UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(
                        user, password, grantedAuths);
                return authenticationContext;

            } else {

                throw new BadCredentialsException("Usuario o contraseña erróneo");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
