package com.vti;

import com.vti.entity.Post;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.form.PostCreateForm;
import com.vti.repository.RoleRepository;
import com.vti.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
@AllArgsConstructor
@SpringBootApplication
public class FinalExamApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(FinalExamApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		var admin = roleRepository.findByType(Role.Type.ADMIN);
		var user = roleRepository.findByType(Role.Type.USER);
		var account = new User();
		account.setName("Trường");
		account.setUsername("truong");
		account.setEmail("truong@gmail.com");
		account.setPassword(passwordEncoder.encode("123456"));
		account.setRoles(Set.of(admin,user));
		userRepository.save(account);

	}
}
