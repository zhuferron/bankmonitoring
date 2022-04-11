package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统信息实体类")
public class SystemInfo implements Serializable {

    @ApiModelProperty("系统ID")
    private String sysId;

    @ApiModelProperty("系统名称")
    private String sysName;

    @ApiModelProperty("系统版本信息")
    private String version;

    @ApiModelProperty("包含的数据库的id")
    private ArrayList<String> dbIds;

    @ApiModelProperty("包含的主机Id")
    private ArrayList<String> hostIds;

    @ApiModelProperty("系统进程ID")
    private ArrayList<String> processIds;

    @ApiModelProperty("系统日志ID")
    private ArrayList<String> logInfoIds;

    @ApiModelProperty("创建时间")
    private Date createTime;

}