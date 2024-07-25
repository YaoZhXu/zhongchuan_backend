package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.dto.Message;
import com.shu.backend.po.Chat;

import java.io.IOException;
import java.util.List;

public interface ChatService extends IService<Chat> {

    List<Chat> selectByTopicId(Long topicId);

    String queryByChatId(Long chatId);

    boolean addReview(Long topicId,String content, String review);

    boolean deleteChat(Long chatId);

    Chat chat(Long topicId, List<Message> messages) throws IOException;

    boolean add(List<Message> messages, Long topicId);

    Page<Chat> list(Integer pageNo, Integer pageSize, Chat chat);

    Page<Chat> queryByUserId(Integer pageNo, Integer pageSize, Long userId);
}
