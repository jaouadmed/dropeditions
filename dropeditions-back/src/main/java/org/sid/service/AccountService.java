package org.sid.service;

import org.sid.entities.AppUser;

public interface AccountService {
  public AppUser saveUser(String username, String email, String password, String confirmedPassword);
  public AppUser saveToken(AppUser user);
  
  public AppUser loadUserByUsername(String username);
  public AppUser loadUserByEmail(String email);
  public AppUser loadUserByResetToken(String username);
  
  
  public void assignRoleToUser(String username, String rolename);
  
  public boolean changePassword(AppUser username, String password, String confirmedPassword);
}
