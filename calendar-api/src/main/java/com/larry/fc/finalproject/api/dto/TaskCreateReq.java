package com.larry.fc.finalproject.api.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Data
public class TaskCreateReq {
	private final String title;
	private final String description;
	private final LocalDateTime taskAt;
}
