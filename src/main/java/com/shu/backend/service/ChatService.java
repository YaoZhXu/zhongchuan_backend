package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Chat;

import java.util.List;

public interface ChatService extends IService<Chat> {

    List<Chat> selectByTopicId(Long topicId);

    String queryByChatId(Long chatId);

    boolean addReview(Long chatId, String review);

    boolean deleteChat(Long chatId);
}
