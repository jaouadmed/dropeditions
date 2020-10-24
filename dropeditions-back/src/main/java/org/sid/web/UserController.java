package org.sid.web;

import lombok.Data;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  UserController {

  @Autowired
  private AccountService accountService;
  
  @PostMapping("/register")
  public AppUser register(@RequestBody UserForm userForm){
    return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
  }
  
  @PostMapping("/passwordForgotten")
  public AppUser passwordForgotten(@RequestBody UserForm userForm){
    return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
  }

}

@Data
class UserForm{
  private String username;
  private String password;
  private String confirmedPassword;
}