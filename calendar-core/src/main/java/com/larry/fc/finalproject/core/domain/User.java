package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

	private Long id;
	private String email;
	private String password;
	private LocalDate birthday;
	private LocalDate createdAt;
}
