package com.mentaldoctor.mentaldoctor.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ApiModel(description = "用户实体类")
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long uuid;

    @NotNull
    @ApiModelProperty(value = "用户名",required = true)
    @Column(unique = true,nullable = false)
    private String username;

    @NotNull
    @ApiModelProperty(value = "密码",required = true)
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ApiModelProperty(value = "是否被锁")
    @Column
    private boolean enable=true;

    @Email
    @ApiModelProperty(value = "邮箱")
    @Column(unique = true)
    private String email;

    @ApiModelProperty(value = "创建日期")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @JsonFormat(pattern = "yyyy-mm-dd",timezone = "GMT+8")
    private Date createTime;

    //TODO:有待
    @Valid
    @ApiModelProperty(value = "用户角色",required = true)
    @NotNull
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH},fetch=FetchType.EAGER)
    private List<Role> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=new ArrayList<>();
        List<Role> roles=this.getRoles();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enable;
    }
}
