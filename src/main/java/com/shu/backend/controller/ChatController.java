package com.shu.backend.controller;

import com.shu.backend.service.ChatService;
import com.shu.backend.vo.request.AddReviewReq;
import com.shu.backend.vo.request.DeleteChatReq;
import com.shu.backend.vo.request.QueryByChatIdReq;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.QueryByChatIdResp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    @GetMapping("/queryByChatId")
    public CommonResponse queryByChatId(@Validated @RequestBody QueryByChatIdReq req) {
        String review = chatService.queryByChatId(req.getChatId());
        QueryByChatIdResp resp = new QueryByChatIdResp();
        resp.setReview(review);
        return CommonResponse.success(resp);
    }

    @PostMapping("/addReview")
    public CommonResponse addReview(@Validated @RequestBody AddReviewReq req) {
        boolean result = chatService.addReview(req.getChatId(), req.getReview());
        if (result) {
            return CommonResponse.success();
        }
        return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "评论添加失败，请重试");

    }

    @PostMapping("/deleteById")
    public CommonResponse deleteReview(@Validated @RequestBody DeleteChatReq req) {
        boolean result = chatService.deleteChat(req.getChatId());
        if (result) {
            return CommonResponse.success();
        }
        return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "消息删除失败，请重试");
    }
}
