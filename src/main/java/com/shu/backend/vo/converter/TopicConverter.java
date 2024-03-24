package com.shu.backend.vo.converter;

import com.shu.backend.po.Topic;
import com.shu.backend.vo.TopicVO;

import java.util.ArrayList;
import java.util.List;

public class TopicConverter {

    public static TopicVO convertTopicToTopicVO(Topic source) {
        TopicVO target = new TopicVO();

        target.setTopicId(source.getId());
        target.setTopicName(source.getName());
        target.setUserId(source.getUserId());
        target.setStartTime(source.getStartTime());
        target.setEndTime(source.getEndTime());

        return target;
    }

    public static List<TopicVO> convertTopicListToTopicVOList(List<Topic> sourceList) {
        List<TopicVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertTopicToTopicVO(source)));

        return targetList;
    }
}
