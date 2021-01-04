package com.example.restagram.config;

import com.example.restagram.domain.users.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //@LoginUser 어노테이션이 들어있으면
        boolean isLoginUser=parameter.getParameterAnnotation(LoginUser.class) != null;
        //sessionUsr 클래스 타입의 파라미터에 @LoginUser어노테이션이 사용되었는가
        boolean isUserClass= SessionUser.class.equals(parameter.getParameterType());
        return isLoginUser && isUserClass;
    }

    //만일 supportsParameter의 리턴값이 false 로 떨어진다면 해당 메소드는 실행되지 않음. true 인경우에만 실행되므로 httpSession에서 "user" 로 저장된 객체를 꺼내오게 됨
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
