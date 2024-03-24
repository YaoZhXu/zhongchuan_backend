package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.User;

public interface UserService extends IService<User> {

    boolean register(User user);

    User login(User user);

    User getUserById(Long userId);
}
