package com.larry.fc.finalproject.api.dto;

import static java.util.stream.Collectors.toList;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import com.larry.fc.finalproject.core.domain.type.TimeUnit;
import java.util.stream.IntStream;
import lombok.Data;

@Data
public class NotificationCreateReq {

	private final String title;
	private final LocalDateTime notifyAt;
	private final RepeatInfo repeatInfo;

	public List<LocalDateTime> getRepeatTimes() {
		if (repeatInfo == null) {
			return Collections.singletonList(notifyAt);
		}

		return IntStream.range(0, repeatInfo.times)  //  반복문 대신에 많이씀 IntStream.range
				.mapToObj(i -> {                      // Stream을 obj 로 형태로 변환
							long increment = (long) repeatInfo.interval.intervalValue * i;
							switch (repeatInfo.interval.timeUnit) {
								case DAY:
									return notifyAt.plusDays(increment);
								case WEEK:
									return notifyAt.plusWeeks(increment);
								case MONTH:
									return notifyAt.plusMonths(increment);
								case YEAR:
									return notifyAt.plusYears(increment);
								default:
									throw new RuntimeException("bad request. not matched time unit");
							}
						}
				).collect(toList());
	}

	@Data
	public static class RepeatInfo {

		private final Interval interval;
		private final int times;
	}

	@Data
	public static class Interval {

		private final int intervalValue;
		private final TimeUnit timeUnit;
	}
}
