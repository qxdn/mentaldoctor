package com.mentaldoctor.mentaldoctor.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ApiModel(description = "前端返回的帖子")
@AllArgsConstructor
@NoArgsConstructor
public class PostBefore {

    @NotNull
    @ApiModelProperty(value = "主题")
    private String title;

    @NotNull
    @ApiModelProperty(value = "内容")
    private String content;

    @Positive
    @ApiModelProperty(value = "用户id")
    private long uuid;
}
