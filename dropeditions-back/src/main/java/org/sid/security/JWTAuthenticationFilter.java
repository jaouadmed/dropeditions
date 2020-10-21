package org.sid.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;
  
  private AppUserRepository appUserRepository;
  
  
  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AppUserRepository appUserRepository) {
    this.authenticationManager = authenticationManager;
    this.appUserRepository = appUserRepository;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    AppUser appUser;
    try {
      appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
      return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword())
      );
    }catch (IOException e){
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();
    List<String> roles = new ArrayList<>();
    authResult.getAuthorities().forEach(au -> {
      roles.add(au.getAuthority());
    });
    String jwt = JWT.create()
            .withIssuer(request.getRequestURI())
            .withSubject(user.getUsername())
            
            .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
            
            .withClaim("apike", appUserRepository.findByUsername(user.getUsername()).getApiKey())
            
            .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPERATION))
            .sign(Algorithm.HMAC256(SecurityParams.SECRET));
    response.addHeader(SecurityParams.JWT_HEADER_NAME, jwt);
    //response.addHeader("apikey", appUserRepository.findByUsername(user.getUsername()).getApiKey());
  }
}
