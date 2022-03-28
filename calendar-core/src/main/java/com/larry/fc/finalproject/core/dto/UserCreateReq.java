package com.larry.fc.finalproject.core.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserCreateReq {
	private final String name;
	private final String email;
	private final String password;
	private final LocalDate birthday;
}
