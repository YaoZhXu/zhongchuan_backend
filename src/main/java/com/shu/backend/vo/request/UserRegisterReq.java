package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterReq {

    @Email(message = "邮件格式校验不通过")
    private String email;

    @Size(min = 6, message = "密码必须大于6位")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Size(max = 8, message = "用户名必须小于8个子")
    @NotBlank(message = "用户名不能为空")
    private String username;
}
