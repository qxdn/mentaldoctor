package com.mentaldoctor.mentaldoctor.servicetest;

import com.mentaldoctor.mentaldoctor.model.dto.ReplyBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import com.mentaldoctor.mentaldoctor.service.api.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void insertTest(){
        ReplyBefore replyBefore=new ReplyBefore();
        replyBefore.setContent("测试回复");
        replyBefore.setPostId(1);
        replyBefore.setUuid(1);
        Reply reply= replyService.insertReply(replyBefore);
        System.out.println(reply);
    }
}
