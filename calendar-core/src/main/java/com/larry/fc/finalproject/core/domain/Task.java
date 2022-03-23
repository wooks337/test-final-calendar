package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

	private Long id;
	private LocalDateTime taskAt;
	private String title;
	private String description;
	private User writer;
	private LocalDate createdAt;
}
