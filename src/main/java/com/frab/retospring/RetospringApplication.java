package com.frab.retospring;

import com.frab.retospring.model.PermissionEntity;
import com.frab.retospring.model.RoleEntity;
import com.frab.retospring.model.RoleEnum;
import com.frab.retospring.model.User;
import com.frab.retospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RetospringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RetospringApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		/* CREATE PERMISSIONS */
		PermissionEntity readUser = PermissionEntity.builder()
				.name("READ_USER")
				.build();


		PermissionEntity createUser = PermissionEntity.builder()
				.name("CREATE_USER")
				.build();

		PermissionEntity invalidUser = PermissionEntity.builder()
				.name("INVALID_USER")
				.build();

		/* CREATE ROLES */
		RoleEntity adminRole = RoleEntity.builder()
				.roleEnum(RoleEnum.ADMIN)
				.permissionList(Set.of(readUser, createUser, invalidUser))
				.build();

		RoleEntity userRole = RoleEntity.builder()
				.roleEnum(RoleEnum.USER)
				.permissionList(Set.of(readUser))
				.build();

		/* CREATE USERS */
		User userAdmin = User.builder()
				.name("ADMIN")
				.email("admin@reto.com")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.active(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(adminRole))
				.build();

		User userUser = User.builder()
				.name("USER")
				.email("user@reto.com")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.active(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(userRole))
				.build();

		userRepository.saveAll(List.of(userAdmin, userUser));
	}

}
