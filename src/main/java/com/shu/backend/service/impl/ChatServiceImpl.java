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
        List<Chat> chatList = list(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .eq(Chat::getTopicId, topicId));
        return chatList;
    }

    @Override
    public String queryByChatId(Long chatId) {
        Chat chat = getOne(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId()));
        if (chat != null) {
            return chat.getReview();
        }
        return null;
    }

    @Override
    public boolean addReview(Long chatId, String review) {
        boolean result = update(new LambdaUpdateWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId())
                .set(Chat::getReview, review));
        return result;
    }

    @Override
    public boolean deleteChat(Long chatId) {
        boolean result = remove(new LambdaQueryWrapper<Chat>()
                .eq(Chat::getId, chatId)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId()));
        return result;
    }
}
