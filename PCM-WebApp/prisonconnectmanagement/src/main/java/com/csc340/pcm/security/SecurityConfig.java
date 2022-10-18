package com.csc340.pcm.security;

import java.util.Collections;

import com.csc340.pcm.views.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    /**
     * Demo SimpleInMemoryUserDetailsManager, which only provides
     * two hardcoded in-memory users and their roles.
     * NOTE: This shouldn't be used in real-world applications.
     */
    private static class SimpleInMemoryUserDetailsManager extends InMemoryUserDetailsManager {
        public SimpleInMemoryUserDetailsManager() {
            createUser(new User("admin",
                    "{noop}admintest",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ));
            createUser(new User("organ",
                    "{noop}organtest",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ORGANIZATION"))
            ));
            createUser(new User("visitor",
                    "{noop}visitortest",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_VISITOR"))
            ));

        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/images/**").permitAll();

        super.configure(http);

        setLoginView(http, LoginView.class);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new SimpleInMemoryUserDetailsManager();
    }
}