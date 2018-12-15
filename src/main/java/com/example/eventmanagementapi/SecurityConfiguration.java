package com.example.eventmanagementapi;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("sanjin").password("sanjin").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("/h2-console/**").permitAll().and();

        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/events").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/events/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/events/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/organizers/**").hasRole("ADMIN")
                .and().csrf().disable();
        http.headers().frameOptions().disable();

    }
}
