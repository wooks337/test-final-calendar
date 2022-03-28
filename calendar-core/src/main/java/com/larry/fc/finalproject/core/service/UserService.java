package com.larry.fc.finalproject.core.service;

import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.UserRepository;
import com.larry.fc.finalproject.core.dto.UserCreateReq;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Builder
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User create(UserCreateReq req) {
		userRepository.findByEmail(req.getEmail())
				.ifPresent(u -> {
					throw new RuntimeException("cannot find user");
				});

		return userRepository.save(User.builder()
				.name(req.getName())
				.password(req.getPassword())
				.email(req.getEmail())
				.birthday(req.getBirthday())
				.build());
	}

	@Transactional
	public Optional<User> findPwMatchUser(String email, String password) {
			return userRepository.findByEmail(email)
					.map(user -> user.getPassword().equals(password) ? user : null);
	}

}
