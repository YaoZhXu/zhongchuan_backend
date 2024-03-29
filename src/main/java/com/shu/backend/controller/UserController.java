package com.shu.backend.controller;

import com.shu.backend.po.User;
import com.shu.backend.service.UserService;
import com.shu.backend.vo.request.UserLoginReq;
import com.shu.backend.vo.request.UserRegisterReq;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.UserLoginResp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.shu.backend.utils.JwtTokenGeneratorUtil.generateJwtToken;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public CommonResponse userRegister(@Validated @RequestBody UserRegisterReq req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setUsername(req.getUsername());

        boolean result = userService.register(user);
        if (result) {
            return CommonResponse.success();
        }
        return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败, email已被注册");
    }

    @PostMapping("/login")
    public CommonResponse userLogin(@Validated @RequestBody UserLoginReq req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        User result = userService.login(user);
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "登录失败");
        }

        UserLoginResp resp = new UserLoginResp();
        resp.setToken(generateJwtToken(String.valueOf(result.getId())));
        return CommonResponse.success(resp);
    }
}
