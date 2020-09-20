package com.sanix.springsecurityjdbc.config;

import com.sanix.springsecurityjdbc.repository.PersonRepository;
import com.sanix.springsecurityjdbc.security.DirectoryUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {

    private PersonRepository personRepository;

    public DirectorySecurityConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(
                new DirectoryUserDetailsService(this.personRepository)
        );
    }
}
