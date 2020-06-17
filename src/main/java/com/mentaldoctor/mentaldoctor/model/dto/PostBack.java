package com.mentaldoctor.mentaldoctor.model.dto;

import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "返回的帖子")
@Data
public class PostBack {

    @ApiModelProperty("主题")
    private Post post;

    @ApiModelProperty("回复")
    private List<Reply> replies;

    @ApiModelProperty("回复数")
    private long totalElements;

    @ApiModelProperty("一页大小")
    private int size;

}
