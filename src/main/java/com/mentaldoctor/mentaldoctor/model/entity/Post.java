package com.mentaldoctor.mentaldoctor.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ApiModel(value = "Post",description = "主题帖子")
public class Post implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "主题")
    @NotNull
    @Column(nullable = false)
    private String title;

    @ApiModelProperty(value = "内容")
    @NotNull
    @Column(nullable = false)
    private String content;

    @ApiModelProperty(value = "创建时间")
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd,hh:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd,hh:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "创建用户")
    @NotNull
    @ManyToOne
    private User user;

    @ApiModelProperty(value = "回复数")
    @Column(nullable = false)
    private Integer replyCounts=0;

    @ApiModelProperty(value = "浏览数")
    @Column(nullable = false)
    private Integer browses=0;
}
