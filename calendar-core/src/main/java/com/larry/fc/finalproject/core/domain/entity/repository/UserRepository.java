package com.larry.fc.finalproject.core.domain.entity.repository;

import com.larry.fc.finalproject.core.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
