package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Chat;
import com.shu.backend.service.ChatService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.ChatVO;
import com.shu.backend.vo.request.chat.*;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import com.shu.backend.vo.response.chat.QueryByChatIdResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

import static com.shu.backend.vo.converter.ChatConverter.*;

@Slf4j
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
        boolean result = chatService.addReview(req.getTopicId(),req.getContent(), req.getReview());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "评论添加失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/deleteById")
    public CommonResponse deleteReview(@Validated @RequestBody DeleteChatReq req) {
        boolean result = chatService.deleteChat(req.getChatId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "消息删除失败");
        }

        return CommonResponse.success();
    }

    @PostMapping("/chat")
    public CommonResponse chat(@Validated @RequestBody ChatReq req) throws IOException {
        Chat result = chatService.chat(req.getTopicId(), req.getMessages());
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "对话失败");
        }
        return CommonResponse.success(convertChatToChatVO(result));
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddChatReq req) {
        boolean result = chatService.add(req.getMessages(), req.getTopicId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加消息失败");
        }

        return CommonResponse.success();
    }

    @PostMapping("/list")
    public CommonResponse list(@Validated  @RequestBody PageListChatReq req){
        Page<Chat> result = chatService.list(req.getPageNo(),req.getPageSize(),convertPageListChatReqToChat(req));

        PageInfo<ChatVO> info  = new PageInfo<>();
        info.fill(result);
        info.setRecords(convertChatListToChatVOList(result.getRecords()));

        return CommonResponse.success(info);

    }

    @PostMapping("/queryByUserId")
    public CommonResponse queryByUserId(@Validated @RequestBody QueryByUserIdReq req){
        Page<Chat> result = chatService.queryByUserId(req.getPageNo(),req.getPageSize(),req.getUserId());

        PageInfo<ChatVO> info = new PageInfo<>();
        info.fill(result);
        info.setRecords(convertChatListToChatVOList(result.getRecords()));
        return CommonResponse.success(info);
    }
}
