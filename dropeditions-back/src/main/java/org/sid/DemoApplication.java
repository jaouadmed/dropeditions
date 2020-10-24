package org.sid;

import java.util.ArrayList;
import java.util.List;

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

			AppRole ar = appRoleRepository.save(new AppRole(null, "USER"));
			
			List<AppRole> ars = new ArrayList<AppRole>();
			ars.add(ar);
			
			appUserRepository.save(new AppUser(null, "joe", bCryptPasswordEncoder.encode("ok"), true, "c3e426520e2bf0a4e540e9b08b078033", ars));
			appUserRepository.save(new AppUser(null, "joe1", bCryptPasswordEncoder.encode("ok1"), true, "c3e426520e2bf0a4e540e9b08b078033", ars));
			appUserRepository.save(new AppUser(null, "joe2", bCryptPasswordEncoder.encode("ok2"), true, "c3e426520e2bf0a4e540e9b08b078033", ars));
			
			appUserRepository.findAll().forEach(p -> System.out.println(p.getUsername()));
			System.out.println(ar);

		};
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}
}
