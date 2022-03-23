package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Event {

	private Long id;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private String title;
	private String description;
	private User writer;
	private List<Engagement> engagements;
	private LocalDate createAt;
}
