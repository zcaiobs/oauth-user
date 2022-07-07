package com.example.oauthuser.config;

import com.example.oauthuser.domain.UserEntity;
import com.example.oauthuser.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FirstUserConfig implements ApplicationRunner {
	
	private final Logger logger = LoggerFactory.getLogger(FirstUserConfig.class);
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public FirstUserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(ApplicationArguments args) {
		if (userRepository.count() != 0) {
			return;
		}
		
		logger.info("Nenhum usuário encontrado, cadastrando usuários padrão.");

		userRepository.save(
				new UserEntity(
						"Admin Teste",
						"admin@email.com",
						passwordEncoder.encode("123456"),
						UserEntity.Type.ADMIN
				)
		);

		userRepository.save(
				new UserEntity(
						"Client Teste",
						"client@email.com",
						passwordEncoder.encode("123456"),
						UserEntity.Type.CLIENT
				)
		);
	}
}
