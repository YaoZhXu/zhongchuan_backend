package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.ChatMapper;
import com.shu.backend.po.Chat;
import com.shu.backend.service.ChatService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Override
    public List<Chat> selectByTopicId(Long topicId) {
        if (topicId == null) {
            return null;
        }

        List<Chat> chatList = list(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .eq(Chat::getTopicId, topicId));
        return chatList;
    }

    @Override
    public String queryByChatId(Long chatId) {
        if (chatId == null) {
            throw new RuntimeException("chatId不能为空");
        }

        Chat chat = getOne(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId()));
        if (chat == null) {
            return null;
        }

        return chat.getReview();
    }

    @Override
    public boolean addReview(Long chatId, String review) {
        if (chatId == null) {
            return false;
        }

        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId());
        if (getOne(wrapper) == null) {
            return false;
        }

        if (review == null) {
            return true;
        }

        return update(new LambdaUpdateWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .set(Chat::getReview, review));
    }

    @Override
    public boolean deleteChat(Long chatId) {
        if (chatId == null) {
            return false;
        }

        return remove(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId()));
    }
}
