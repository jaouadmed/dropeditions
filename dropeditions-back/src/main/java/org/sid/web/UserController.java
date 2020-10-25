package org.sid.web;

import lombok.Data;

import java.util.Map;
import java.util.UUID;


import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.sid.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  UserController {

  @Autowired
  private AccountService accountService;
  
  @Autowired
  private EmailService emailService;
  
  @PostMapping("/register")
  public AppUser register(@RequestBody UserForm userForm){
    return accountService.saveUser(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), userForm.getConfirmedPassword());
  }
  
  @PostMapping("/passwordForgotten")
	public boolean passwordForgotten(@RequestBody UserEmail email) {
		AppUser user = accountService.loadUserByEmail(email.getEmail());
		if (user != null) {
			user.setResetToken(UUID.randomUUID().toString());
			accountService.saveToken(user);
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@dropeditions.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, use this token:\n" + user.getResetToken());
			emailService.sendEmail(passwordResetEmail);
			return true;
		}
		throw new RuntimeException("Not able to find user with this email!");
	}
  
  @PostMapping("/passwordChange")
	public boolean passwordChange(@RequestBody UserChange user) {
		AppUser user1 = accountService.loadUserByResetToken(user.getToken());
		System.out.println(user1);
		if (user1 != null && accountService.changePassword(user1, user.getPassword(), user.getConfirmedPassword()))
			return true;
		
		throw new RuntimeException("No valid token found!");
  }
}

@Data
class UserForm{
  private String username;
  private String email;
  private String password;
  private String confirmedPassword;
  
}

@Data
class UserEmail{
  private String email; 
}

@Data
class UserChange{
  private String token;
  private String password;
  private String confirmedPassword;

}
