package com.mentaldoctor.mentaldoctor.controller;

import com.mentaldoctor.mentaldoctor.model.dto.ReplyBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import com.mentaldoctor.mentaldoctor.service.api.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
@Api(tags = "回复操作")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @ApiOperation("发表回复")
    @ApiImplicitParam(paramType = "body")
    @PostMapping("/")
    public Reply insertReply(@RequestBody @Validated ReplyBefore replyBefore){
        return replyService.insertReply(replyBefore);
    }
}
