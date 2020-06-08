package com.mentaldoctor.mentaldoctor.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@ApiModel(value = "用户角色类")
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
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
