package com.shu.backend.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shu.backend.po.User;
import com.shu.backend.service.UserService;
import com.shu.backend.utils.JwtTokenGeneratorUtils;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.utils.UserInfoContext;
import com.shu.backend.vo.response.CommonResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String auth = request.getHeader("Authorization");
        if (auth == null || auth.trim().isEmpty()) {
            sendErrorResponse(response, "身份认证信息缺失，请重新登录", 401);
            return false;
        }

        try {
            auth = auth.substring("Bearer ".length());
            String parserId = JwtTokenGeneratorUtils.parserJwtToken(auth).getSubject();
            if (parserId == null) {
                sendErrorResponse(response, "身份信息解析失败，请重新登录", 401);
                return false;
            }

            Long userId = Long.valueOf(parserId);
            User user = userService.getUserById(userId);
            if (user == null) {
                sendErrorResponse(response, "用户不存在，请重新登录", 401);
                return false;
            }

            UserInfoContext userInfo = new UserInfoContext();
            userInfo.setUserId(user.getId());
            userInfo.setEmail(user.getEmail());
            userInfo.setUsername(user.getUsername());
            UserContextHolder.setUserInfo(userInfo);
            return true;
        } catch (Exception e) {
            sendErrorResponse(response, "身份信息过期，请重新登录", 401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContextHolder.clear();
    }

    private void sendErrorResponse(HttpServletResponse response, String message, int statusCode) throws IOException {
        CommonResponse<String> resp = CommonResponse.fail(statusCode, message);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(resp));
    }
}
