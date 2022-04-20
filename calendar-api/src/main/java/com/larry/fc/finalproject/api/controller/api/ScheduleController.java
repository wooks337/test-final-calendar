package com.larry.fc.finalproject.api.controller.api;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.TaskCreateReq;
import com.larry.fc.finalproject.api.service.TaskService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

	private final TaskService taskService;

	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq, HttpSession session) {
		final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
		if (userId == null) {
			throw new RuntimeException("bad request. no session.");
		}
		taskService.create(taskCreateReq, AuthUser.of(userId));
		return ResponseEntity.ok().build();  // 200 ok 에 body 없음
	}
}