package com.shu.backend.controller;

import com.shu.backend.po.Chat;
import com.shu.backend.po.Topic;
import com.shu.backend.service.ChatService;
import com.shu.backend.service.TopicService;
import com.shu.backend.vo.ChatVO;
import com.shu.backend.vo.TopicVO;
import com.shu.backend.vo.converter.ChatConverter;
import com.shu.backend.vo.converter.TopicConverter;
import com.shu.backend.vo.request.ClearTopicByTopicIdReq;
import com.shu.backend.vo.request.DeleteTopicReq;
import com.shu.backend.vo.request.GetHisChatListByTopicIdReq;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.GetHisChatListByTopicIdResp;
import com.shu.backend.vo.response.GetUserTopicListResp;
import com.shu.backend.vo.response.TopicAddResp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Resource
    private TopicService topicService;

    @Resource
    private ChatService chatService;

    @PostMapping("/add")
    public CommonResponse addTopic() {
        Long topicId = topicService.insert();
        if (topicId == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建对话失败");
        }

        TopicAddResp resp = new TopicAddResp();
        resp.setTopicId(topicId);
        return CommonResponse.success(resp);
    }

    @GetMapping("/getUserTopicList")
    public CommonResponse getUserTopicList() {
        List<Topic> topicList = topicService.getUserTopicList();
        List<TopicVO> topicVOList = TopicConverter.convertTopicListToTopicVOList(topicList);
        GetUserTopicListResp resp = new GetUserTopicListResp();
        resp.setTopicList(topicVOList);
        return CommonResponse.success(resp);
    }

    @PostMapping("/getHisChatListByTopicId")
    public CommonResponse getHisChatListByTopicId(@Validated @RequestBody GetHisChatListByTopicIdReq req) {
        List<Chat> chatList = chatService.selectByTopicId(req.getTopicId());
        List<ChatVO> chatVOList = ChatConverter.convertChatListToChatVOList(chatList);
        GetHisChatListByTopicIdResp resp = new GetHisChatListByTopicIdResp();
        resp.setChatList(chatVOList);
        return CommonResponse.success(resp);
    }

    @PostMapping("/clearTopicByTopicId")
    public CommonResponse clearTopicByTopicId(@Validated @RequestBody ClearTopicByTopicIdReq req) {
        boolean result = topicService.clearTopicByTopicId(req.getTopicId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "对话清空失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public CommonResponse deleteTopic(@Validated @RequestBody DeleteTopicReq req) {
        boolean result = topicService.delete(req.getTopicId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "对话删除失败");
        }
        return CommonResponse.success();
    }
}
