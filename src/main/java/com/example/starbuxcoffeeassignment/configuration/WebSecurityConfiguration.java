package com.example.starbuxcoffeeassignment.configuration;

import com.example.starbuxcoffeeassignment.authentication.JwtTokenFilter;
import com.example.starbuxcoffeeassignment.authentication.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserDetailService userDetailsService;


    @Autowired
    public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/h2-console/**").permitAll() // added for h2-console
                .antMatchers("/v2/api-docs/**",  "/v3/api-docs/**",
                                         "/", "/csrf",
                                         "/configuration/ui",
                                         "/swagger-resources/**",
                                         "/configuration/security",
                                         "/swagger-ui.html",
                                        "/swagger-ui",
                                        "/swagger-ui/**",
                                         "/swagger-ui.html#/**",
                                         "/webjars/**").permitAll()  // added for swagger
                 .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();// added for h2-console

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs/**",
                "/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-ui.html#/**",
                "/webjars/**");


    }

}