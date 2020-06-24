package org.mvn.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token =tokenProvider.resolveToken((HttpServletRequest) servletRequest);
        if(token!=null && tokenProvider.validateToken(token)){
            Authentication auth=tokenProvider.getAuth(token);
            if(auth !=null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
