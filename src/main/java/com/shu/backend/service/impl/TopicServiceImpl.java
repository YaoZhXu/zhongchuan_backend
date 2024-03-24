package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.ChatMapper;
import com.shu.backend.mapper.TopicMapper;
import com.shu.backend.po.Chat;
import com.shu.backend.po.Topic;
import com.shu.backend.service.TopicService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.*;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Resource
    private ChatMapper chatMapper;

    @Override
    public Long insert() {
        Topic topic = new Topic();
        topic.setName(localDatetime2String(getCurrentLocalDateTime()));
        topic.setUserId(UserContextHolder.getUserInfo().getUserId());
        topic.setStartTime(getCurrentLocalDateTime());
        topic.setEndTime(getCurrentLocalDateTime());

        save(topic);
        return topic.getId();
    }

    @Override
    public List<Topic> getUserTopicList() {
        List<Topic> topicList = list(new LambdaQueryWrapper<Topic>()
                .eq(Topic::getUserId, UserContextHolder.getUserInfo().getUserId()));
        return topicList;
    }

    @Override
    @Transactional
    public boolean clearTopicByTopicId(Long topicId) {
        Long count = chatMapper.selectCount(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .eq(Chat::getTopicId, topicId));
        if (count == 0) {
            return true;
        }

        int result = chatMapper.delete(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .eq(Chat::getTopicId, topicId));
        return result > 0;
    }

    @Override
    @Transactional
    public boolean deleteTopic(Long topicId) {
        boolean rmChats = clearTopicByTopicId(topicId);
        boolean rmTopic = remove(new LambdaQueryWrapper<Topic>()
                .eq(Topic::getId, topicId)
                .eq(Topic::getUserId, UserContextHolder.getUserInfo().getUserId()));
        return rmChats & rmTopic;
    }
}
