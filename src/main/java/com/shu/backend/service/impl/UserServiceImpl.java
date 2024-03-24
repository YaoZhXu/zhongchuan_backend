package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.UserMapper;
import com.shu.backend.po.User;
import com.shu.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean register(User user) {
        List<User> userList = list(new LambdaQueryWrapper<User>().eq(User::getEmail, user.getEmail()));
        if (userList.isEmpty()) {
            return save(user);
        }
        return false;
    }

    @Override
    public User login(User user) {
        User result = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, user.getEmail())
                .eq(User::getPassword, user.getPassword()));
        return result;
    }

    @Override
    public User getUserById(Long userId) {
        User user = getById(userId);
        return user;
    }
}
