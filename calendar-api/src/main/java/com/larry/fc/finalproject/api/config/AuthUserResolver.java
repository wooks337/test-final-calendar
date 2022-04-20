package com.larry.fc.finalproject.api.config;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

import com.larry.fc.finalproject.api.dto.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/*
* HandlerMethodArgumentResolver 는 spring 프레임워크에서 Default로 알게모르게 사용하고 있음 !!!
* */
public class AuthUserResolver implements HandlerMethodArgumentResolver {

	/**
	 * supportsParameter :  파라미터가 AuthUser 라면  resolveArgument가 호출되게  등록하는 기능
	 * 강좌에서는 FakeAuthUserResolver 를
	 */
		@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return AuthUser.class.isAssignableFrom(parameter.getParameterType());
		/*
		* Object b 가 A class를 상속받았거나 다른 인터페이스를 구현했는지 체크한다.
		즉 인터페이스 및 상위 클래스 구현여부를 체크하는 것이다.
		* */
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			// 즉 resolveArgument 를 통해 api 호출시 session 이 있는지 체크하기위해 커스컴 HandlerMethodArgumentResolver 를 만들어줌 !!!
		final Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY,
				WebRequest.SCOPE_SESSION);
		if (userId != null) {
			return AuthUser.of(userId);
		} else {
			throw new RuntimeException("bad request. no session");
		}
	}
}
