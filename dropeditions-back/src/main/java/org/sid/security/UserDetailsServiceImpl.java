package org.sid.security;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByUsername(s);
    if(appUser == null) throw new UsernameNotFoundException("invalid user");
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    appUser.getRoles().forEach(r->{
      grantedAuthorities.add(new SimpleGrantedAuthority(r.getRoleName()));
    });
    return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
  }
}
