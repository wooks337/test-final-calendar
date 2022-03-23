package com.larry.fc.finalproject.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private LocalDateTime createAt;

	public Event(Long id, LocalDateTime startAt, LocalDateTime endAt, String title, String description,
			User writer, List<Engagement> engagements, LocalDateTime createAt) {
		this.id = id;
		this.startAt = startAt;
		this.endAt = endAt;
		this.title = title;
		this.description = description;
		this.writer = writer;
		this.engagements = engagements;
		this.createAt = createAt;
	}

	public void addEngagement(Engagement engagement) {
		if (this.getEngagements() == null) {
			this.engagements = new ArrayList<>();
		}
		this.engagements.add(engagement);
	}
}
