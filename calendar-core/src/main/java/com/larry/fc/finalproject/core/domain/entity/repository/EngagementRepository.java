package com.larry.fc.finalproject.core.domain.entity.repository;

import com.larry.fc.finalproject.core.domain.entity.Engagement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
	    List<Engagement> findAllByAttendeeIdInAndSchedule_EndAtAfter(List<Long> attendeeIds, LocalDateTime startAt);
}
