package org.mvn.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mvn.security.jwt.JwtConfigurer;
import org.mvn.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception{
        return super.authenticationManagerBean();

    }

    protected void configure(HttpSecurity http) throws Exception{
     http
             .httpBasic().disable()
             .csrf().disable()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
              .authorizeRequests()
              .antMatchers("/auth/**","swagger-ui.html/*","/api/docs/**").permitAll()
              .antMatchers("/api/**").authenticated()
              .antMatchers("/users").denyAll()
              .and()
              .apply(new JwtConfigurer(jwtTokenProvider));
    }

}
