package org.sid.service;

import org.sid.entities.AppUser;

public interface AccountService {
  public AppUser saveUser(String username, String password, String confirmedPassword);

  public AppUser loadUserByUsername(String username);
  
  public void assignRoleToUser(String username, String rolename);
  
  public boolean changePassword(String username, String password, String confirmedPassword);
}
