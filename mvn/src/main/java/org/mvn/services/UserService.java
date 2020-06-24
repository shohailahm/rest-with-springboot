package org.mvn.services;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.mvn.data.model.User;
import org.mvn.exceptions.ResourceNotFoundException;
import org.mvn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public UserService(UserRepository repository){
        this.repository=repository;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User user=repository.findByUserName(s);
       if(user !=null){
           return user;
       } throw new UsernameNotFoundException("User Doesn't exist");
    }
}
