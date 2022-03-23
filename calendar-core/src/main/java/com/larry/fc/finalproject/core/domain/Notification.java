package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Notification {

	private Long id;
	private LocalDateTime notifyAt;
	private String title;
	private String description;
	private User writer;
	private LocalDate createdAt;
}
