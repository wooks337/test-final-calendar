package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Engagement {

	private Long id;
	private Event event;
	private User attendee;
	private RequestStatus status;
	private LocalDate createdAt;
}
