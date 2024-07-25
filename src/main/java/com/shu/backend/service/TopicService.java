package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Topic;

import java.util.List;

public interface TopicService extends IService<Topic> {

    Long insert();

    List<Topic> getUserTopicList();

    boolean clearTopicByTopicId(Long topicId);

    boolean delete(Long topicId);

    boolean rename(Long topicId, String name);
}
