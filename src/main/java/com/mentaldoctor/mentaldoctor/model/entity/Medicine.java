package com.mentaldoctor.mentaldoctor.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(description = "药物")
public class Medicine {

    @ApiModelProperty(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(name = "药名")
    @NotNull
    @Column(nullable = false,length = 25,unique = true)
    @Length(max = 25)
    private String name;

    @ApiModelProperty(name = "价格")
    @NotNull
    @Column(nullable = false)
    private double price;
}
