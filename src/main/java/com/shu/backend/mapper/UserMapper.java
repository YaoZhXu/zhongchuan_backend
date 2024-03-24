package com.shu.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shu.backend.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
