package org.sid;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	  private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return args -> {

			appUserRepository.deleteAll();
			appRoleRepository.deleteAll();

			appRoleRepository.save(new AppRole(null, "USER"));
			
			
			appUserRepository.save(new AppUser(null, "joe", bCryptPasswordEncoder.encode("ok"), true, "ok", null));
			appUserRepository.save(new AppUser(null, "joe1", bCryptPasswordEncoder.encode("ok1"), true, "ok1", null));
			appUserRepository.save(new AppUser(null, "joe2", bCryptPasswordEncoder.encode("ok2"), true, "ok2", null));

			appUserRepository.findAll().forEach(p -> System.out.println(p.getUsername()));

		};
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}
}
