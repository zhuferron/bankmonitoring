package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//邮件实体类
@ApiModel("邮件实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {

    @ApiModelProperty("标题")
    private String subject;

    @ApiModelProperty("正文")
    private String text;

    @ApiModelProperty("接收邮箱")
    private String to;

    @ApiModelProperty("发送邮箱")
    private String from = "2963624004@qq.com";

}
