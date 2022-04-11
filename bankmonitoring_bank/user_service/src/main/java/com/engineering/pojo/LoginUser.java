package com.engineering.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//实体类
@ApiModel("用户实体类")
@TableName(value = "login_user")
public class LoginUser implements Serializable {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户角色")
    private String role;
    @ApiModelProperty("用户邮箱")
    private String email;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("用户创建时间")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("用户修改时间")
    private Date updateTime;
    @Version
    @ApiModelProperty("乐观锁")
    private Integer version;
}