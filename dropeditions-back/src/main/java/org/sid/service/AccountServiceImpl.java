package org.sid.service;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AppUserRepository appUserRepository;
  @Autowired
  private AppRoleRepository appRoleRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public AppUser saveUser(String username, String email, String password, String confirmedPassword) {
    AppUser user = appUserRepository.findByUsername(username);
    if(user != null) throw new RuntimeException("User already exists!");
    
    if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password!");
    
    AppUser appUser = new AppUser();
    appUser.setId(null);
    appUser.setUsername(username);
    appUser.setEmail(email);
    appUser.setPassword(bCryptPasswordEncoder.encode(password));
    appUser.setActive(true);
    appUserRepository.save(appUser);
    assignRoleToUser(username, "USER");
    return appUser;
  }
  
  @Override
	public AppUser saveToken(AppUser user) {
		return appUserRepository.save(user);
	}

  @Override
  public AppUser loadUserByUsername(String username) {
    return appUserRepository.findByUsername(username);
  }
  
  @Override
	public AppUser loadUserByEmail(String email) {
		// TODO Auto-generated method stub
		return appUserRepository.findByEmail(email);
	}
  
  @Override
	public AppUser loadUserByResetToken(String resetToken) {
		// TODO Auto-generated method stub
		return appUserRepository.findByResetToken(resetToken);
	}
	

  @Override
	public void assignRoleToUser(String username, String rolename) {
	    AppUser appUser = appUserRepository.findByUsername(username);
	    AppRole appRole = appRoleRepository.findByRoleName(rolename);
	    appUser.getRoles().add(appRole);
	}
  
  @Override
	public boolean changePassword(AppUser user, String password, String confirmedPassword) {
	    if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password!");
	    user.setPassword(bCryptPasswordEncoder.encode(password));
	    appUserRepository.save(user);
		return true;
	}
}
