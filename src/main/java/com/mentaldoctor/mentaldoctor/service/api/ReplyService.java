package com.mentaldoctor.mentaldoctor.service.api;

import com.mentaldoctor.mentaldoctor.model.dto.ReplyBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;

public interface ReplyService {

    public Reply insertReply(ReplyBefore replyBefore);
}
