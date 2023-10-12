package com.example.boardgamespringbootminiproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class configures the security that only allows access to authorized users
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //must be authorized to gain access to endpoints
public class SecurityConfiguration {

    /**
     * Provides a BCrypt password encoder bean for password hashing.
     *
     * @return The BCryptPasswordEncoder bean.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a JwtRequestFilter bean for handling JWT authentication.
     *
     * @return The JwtRequestFilter bean.
     */
    @Bean
    public JwtRequestFilter authJwtRequestFilter(){
        return new JwtRequestFilter();
    }

    /**
     * Configures security filters and permissions for HTTP requests.
     *
     * @param http The HttpSecurity object for configuring security.
     * @return The configured SecurityFilterChain.
     * @throws Exception if there is a security configuration exception.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/users/", "/auth/users/login/", "/auth/users/register/").permitAll()
                .antMatchers("/h2-console/**").permitAll()  //DO NOT NEED THIS LINE IN PRODUCTION
                .anyRequest().authenticated() //any request, must be authenticated
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //store session data on client side
                .and().csrf().disable()//filters the traffic by disabling all access "cross site request forgery"
                .headers().frameOptions().disable(); //without this line the ui will not be framed together. DO NOT NEED THIS LINE IN PRODUCTION
        http.addFilterBefore(authJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); //calls the method we created
        return http.build(); //return the http
    }

    /**
     * Provides an AuthenticationManager bean.
     *
     * @param authConfig The AuthenticationConfiguration for getting the AuthenticationManager.
     * @return The AuthenticationManager bean.
     * @throws Exception if there is an exception while obtaining the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
