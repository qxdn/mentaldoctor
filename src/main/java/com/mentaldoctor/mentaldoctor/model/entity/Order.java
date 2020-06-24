package com.mentaldoctor.mentaldoctor.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "ordertable")
@Data
@ApiModel(description = "订单")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private long doctorId;

    @NotNull
    @Column(nullable = false)
    private long buyId;

    @ApiModelProperty(value = "创建时间")
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @NotNull
    @Column(nullable = false)
    private Integer medicineId;

    @NotNull
    @Column(nullable = false)
    private Integer number;
}
