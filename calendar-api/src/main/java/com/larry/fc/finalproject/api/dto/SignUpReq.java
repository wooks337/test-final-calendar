package com.larry.fc.finalproject.api.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SignUpReq {
	private  final String name;
	private  final String email;
	private  final  String password;
	private  final LocalDate birthday;
}
