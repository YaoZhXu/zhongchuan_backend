package com.shu.backend.vo.converter;

import com.shu.backend.po.Chat;
import com.shu.backend.vo.ChatVO;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;

public class ChatConverter {

    public static ChatVO convertChatToChatVO(Chat source) {
        if (source == null) {
            return null;
        }

        ChatVO target = new ChatVO();

        target.setChatId(source.getId());
        target.setTopicId(source.getTopicId());
        target.setUserId(source.getUserId());
        target.setSenderId(source.getSenderId());
        target.setRole(source.getRole());
        target.setContent(source.getContent());
        target.setSendTime(localDatetime2String(source.getSendTime()));
//        target.setSourceLinks(Arrays.asList(source.getSourceLinks().split(",")));
        target.setReview(source.getReview());

        return target;
    }

    public static List<ChatVO> convertChatListToChatVOList(List<Chat> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<ChatVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertChatToChatVO(source)));

        return targetList;
    }
}
