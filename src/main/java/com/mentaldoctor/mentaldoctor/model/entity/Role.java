package com.mentaldoctor.mentaldoctor.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@ApiModel(description = "用户角色类")
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "角色ID")
    private int id;

    @NotNull
    @ApiModelProperty(value = "角色名",required = true)
    @Column(nullable = false,unique = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "角色中文名",required = true)
    @Column(nullable = false)
    private String description;
}
