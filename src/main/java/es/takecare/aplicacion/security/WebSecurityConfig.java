	package es.takecare.aplicacion.security;


	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig {

		@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers(
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/webjars/**"),
                new AntPathRequestMatcher("/imagenes/**"),
                new AntPathRequestMatcher("/error")
            ).permitAll()
            .anyRequest().authenticated()
        )
        .formLogin((form) -> form
            .loginPage("/login")
            .permitAll()
        )
        .logout((logout) -> logout.permitAll())
        .csrf(CsrfConfigurer::disable);
        
        http.headers().frameOptions().disable();
    return http.build();
}


	}
