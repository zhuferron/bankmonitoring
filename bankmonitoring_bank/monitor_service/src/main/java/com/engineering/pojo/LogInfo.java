package com.engineering.pojo;

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
@ApiModel("日志信息实体类")
public class LogInfo implements Serializable {

    @ApiModelProperty("生成日志的ID")
    private String logId;

    @ApiModelProperty("所属系统对应的id")
    private String sysId;

    @ApiModelProperty("日志内容")
    private String infoContent;

    @ApiModelProperty("0成功，1失败")
    private String state;

    @ApiModelProperty("创建时间")
    private Date createTime;


}