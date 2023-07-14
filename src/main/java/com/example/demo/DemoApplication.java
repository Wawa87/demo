package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = new User();
		user.setUsername("dev");
		user.setPassword(bCryptPasswordEncoder.encode("1234"));
		user.setEnabled(true);

		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		userRepository.save(user);
		authorityRepository.save(authority);
		authority.setId(null);
		authority.setAuthority("ROLE_ADMIN");
		authorityRepository.save(authority);
		authority.setId(null);
		authority.setAuthority("ROLE_DEV");
		authorityRepository.save(authority);
	}
}
