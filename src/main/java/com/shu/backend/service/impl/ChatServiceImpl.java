package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.dto.ChatDTO;
import com.shu.backend.dto.Message;
import com.shu.backend.facade.ChatFacade;
import com.shu.backend.mapper.ChatMapper;
import com.shu.backend.mapper.TopicMapper;
import com.shu.backend.po.Chat;
import com.shu.backend.service.ChatService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.shu.backend.utils.DateConverterUtils.getCurrentLocalDateTime;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private ChatFacade chatFacade;

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
    public boolean addReview(Long topicId,String content, String review) {
        if (topicId == null) {
            return false;
        }

        // 查询数据库是否存在当前chat
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<Chat>()
                .eq(Chat::getTopicId, topicId)
                .like(Chat::getContent,content)
                .eq(Chat::getUserId, UserContextHolder.getUserInfo().getUserId());

        if (getOne(wrapper) == null) {
            return false;
        }

        if (review == null) {
            return true;
        }
        // 存在且review不为null，更新当前chat
        return update(new LambdaUpdateWrapper<Chat>()
                .eq(Chat::getTopicId, topicId)
                .like(Chat::getContent,content)
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

    @Override
    public Chat chat(Long topicId, List<Message> messages) throws IOException {
        if (topicId == null || messages.isEmpty()) {
            throw new RuntimeException();
        }

        if (topicMapper.selectById(topicId) == null) {
            throw new RuntimeException();
        }

        Message lastMsg = messages.get(messages.size() - 1);
        Chat chat = new Chat();
        chat.setTopicId(topicId);
        chat.setUserId(UserContextHolder.getUserInfo().getUserId());
        chat.setSenderId(UserContextHolder.getUserInfo().getUserId());
        chat.setRole(lastMsg.getRole());
        chat.setContent(lastMsg.getContent());
        chat.setSendTime(getCurrentLocalDateTime());
        save(chat);

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setMessages(messages);
        String answer = chatFacade.chat(chatDTO);

        chat = new Chat();
        chat.setTopicId(topicId);
        chat.setUserId(UserContextHolder.getUserInfo().getUserId());
        chat.setRole("assistant");
        chat.setContent(answer);
        chat.setSendTime(getCurrentLocalDateTime());
        save(chat);

        return chat;
    }

    @Override
    @Transactional
    public boolean add(List<Message> messages, Long topicId) {
        if (messages.isEmpty()) {
            return false;
        }

        List<Chat> chatList = new ArrayList<>();
        messages.forEach((message -> {
            Chat chat = new Chat();
            chat.setTopicId(topicId);
            chat.setRole(message.getRole());
            chat.setUserId(UserContextHolder.getUserInfo().getUserId());
            if (Objects.equals(message.getRole(), "user")) {
                chat.setSenderId(UserContextHolder.getUserInfo().getUserId());
            }
            chat.setContent(message.getContent());
            chat.setSendTime(getCurrentLocalDateTime());
            chatList.add(chat);
        }));
        saveBatch(chatList);
        return true;
    }

    @Override
    public Page<Chat> list(Integer pageNo, Integer pageSize, Chat chat) {
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        if(chat.getId()!=null){
            wrapper.eq(Chat::getId,chat.getId());
        }
        if(chat.getTopicId()!=null){
            wrapper.like(Chat::getTopicId,chat.getTopicId());
        }
        if(chat.getUserId()!=null){
            wrapper.like(Chat::getUserId,chat.getUserId());
        }
        if(chat.getRole()!=null){
            wrapper.like(Chat::getRole,chat.getRole());
        }
        if(chat.getContent()!=null){
            wrapper.like(Chat::getContent,chat.getContent());
        }

        Page<Chat> p = new Page<>(pageNo,pageSize);
        return page(p,wrapper);

    }

    @Override
    public Page<Chat> queryByUserId(Integer pageNo, Integer pageSize, Long userId) {

        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        if(userId==null){
            return null;
        }
        wrapper.eq(Chat::getUserId,userId);
        Page<Chat> p = new Page<>(pageNo,pageSize);
        return page(p,wrapper);

    }
}
