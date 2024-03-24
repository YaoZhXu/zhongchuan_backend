package com.shu.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shu.backend.po.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
