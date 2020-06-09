package com.mentaldoctor.mentaldoctor.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "返回值")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "消息")
    private String msg;
    @ApiModelProperty(value = "主体")
    private Object obj;

    public static RespBean ok(String msg,Object obj){
        return new RespBean(200,msg,obj);
    }

    public static RespBean error(String msg,Object obj){
        return new RespBean(500,msg,obj);
    }

    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }
}
