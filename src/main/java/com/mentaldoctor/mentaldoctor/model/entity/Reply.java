package com.mentaldoctor.mentaldoctor.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ApiModel(value = "Reply",description = "回复")
public class Reply implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "回复内容")
    @Column(nullable = false)
    private String content;

    @ApiModelProperty(value = "创建时间")
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd,hh:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "回复主题")
    @ManyToOne
    private Post post;

    @ApiModelProperty(value = "用户")
    @ManyToOne
    private User user;
}
