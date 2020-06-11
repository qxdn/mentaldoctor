package com.mentaldoctor.mentaldoctor.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(description = "前端传回回复")
@Data
public class ReplyBefore {

    @NotNull
    @ApiModelProperty(value = "回复内容")
    private String content;

    @NotNull
    @ApiModelProperty(value = "用户id")
    private long uuid;

    @NotNull
    @ApiModelProperty(value = "主题id")
    private int postId;
}
