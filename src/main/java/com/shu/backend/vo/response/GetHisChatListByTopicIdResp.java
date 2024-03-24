package com.shu.backend.vo.response;

import com.shu.backend.vo.ChatVO;
import lombok.Data;

import java.util.List;

@Data
public class GetHisChatListByTopicIdResp {

    private List<ChatVO> chatList;
}
