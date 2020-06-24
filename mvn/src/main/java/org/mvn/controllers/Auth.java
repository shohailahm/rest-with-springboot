package org.mvn.controllers;

import static org.springframework.http.ResponseEntity.ok;
import io.swagger.annotations.ApiOperation;
import org.mvn.data.model.User;
import org.mvn.data.vo.Booksvo;
import org.mvn.repository.UserRepository;
import org.mvn.security.AccountCredentialsvo;
import org.mvn.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @PostMapping(value = "/signin")
    @ApiOperation(value = "authenticate User")
    public ResponseEntity signin(@RequestBody AccountCredentialsvo data){
        try{
            System.out.println(data);
            String username=data.getUserName();
            String password=data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

            User user = repository.findByUserName(username);

            String token="";
            if(user!=null){


                token=tokenProvider.createToken(username,user.getRoles());
            }else{
                throw new UsernameNotFoundException("username not found");
            }

            Map<Object,Object> model=new HashMap<>();
            model.put("username",username);
            model.put("token",token);

               return ok(model);
        }catch (Exception e){
            System.out.println(e);
            throw new BadCredentialsException("Invalid username password");
        }
    }
}
