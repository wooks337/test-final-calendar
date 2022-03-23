package com.larry.fc.finalproject.core.domain;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainCreateTest {

	@Test
	@DisplayName("이벤트 생성")
	void createEvent() {
		final User user1 = new User(1L, "user1", "test@email", "password", LocalDateTime.now().toLocalDate(), // 날짜 형식으로 변환
				LocalDateTime.now());

		final User user2 = new User(2L, "user2", "test2@email", "password", LocalDateTime.now().toLocalDate(), // 날짜 형식으로 변환
				LocalDateTime.now());

		final Event event = new Event(1L, LocalDateTime.now(), LocalDateTime.now(), "급만남", "내용무", user1, null,
				LocalDateTime.now());

		final Engagement engagement = new Engagement(1L, event, user2, RequestStatus.REQUESTED, LocalDateTime.now());
		event.addEngagement(engagement);

		assertEquals("user1", event.getEngagements().get(0).getEvent().getWriter().getName());
	}
}
