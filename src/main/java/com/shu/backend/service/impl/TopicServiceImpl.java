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
                .eq(Topic::getUserId, UserContextHolder.getUserInfo().getUserId())
                .orderByDesc(Topic::getStartTime));
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

        chatMapper.delete(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .eq(Chat::getTopicId, topicId));
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long topicId) {
        if (topicId == null) {
            return false;
        }

        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<Topic>()
                .eq(Topic::getId, topicId)
                .eq(Topic::getUserId, UserContextHolder.getUserInfo().getUserId());
        if (getOne(wrapper) == null) {
            return false;
        }

        clearTopicByTopicId(topicId);
        remove(wrapper);
        return true;
    }
}
