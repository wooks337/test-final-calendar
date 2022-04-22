package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.EventCreateReq;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  // 서비스 레이어, 내부에서 자바 로직을 처리함
@RequiredArgsConstructor
public class EventService {

	private final UserService userService;
	private final ScheduleRepository scheduleRepository;
	private final EngagementRepository engagementRepository;
	private final EmailService emailService;

	@Transactional
	public void create(EventCreateReq req, AuthUser authUser) {
		// attendees 의 스케쥴 시간과 겹치치 않는지 ?
		final List<Engagement> engagementList = engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(
				req.getAttendeeIds(), req.getStartAt());

		/*
		*  stream
		* 스트림은 '데이터의 흐름’입니다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를
		* 필터링하고 가공된 결과를 얻을 수 있습니다. 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할
		* 수 있습니다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있습니다.
		*
		* 스트림에 대한 내용은 크게 세 가지로 나눌 수 있습니다.
			생성하기 : 스트림 인스턴스 생성.
			가공하기 : 필터링(filtering) 및 맵핑(mapping) 등 원하는 결과를 만들어가는 중간 작업(intermediate operations).
			결과 만들기 : 최종적으로 결과를 만들어내는 작업(terminal operations).
		* */

		if (engagementList
				.stream()
				.anyMatch(engagement -> engagement.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
						&& engagement.getStatus() == RequestStatus.ACCEPTED)) {
			throw new RuntimeException("cannot make engagement. period overlapped.");
		}
		final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(),
				req.getEndAt(), userService.getOrThrowById(authUser.getId()));
		scheduleRepository.save(eventSchedule);
		req.getAttendeeIds().stream()
				.map(userService::getOrThrowById)
				.forEach(user -> {
					final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
					emailService.sendEngagement(e);
				});
	}

}

