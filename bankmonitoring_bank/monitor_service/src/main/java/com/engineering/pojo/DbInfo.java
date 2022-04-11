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
@ApiModel("系统数据库信息实体类")
public class DbInfo implements Serializable{
    @ApiModelProperty("数据库ID")
    private String dbId;

    @ApiModelProperty("数据库名称")
    private String dbName;

    @ApiModelProperty("数据库类型")
    private String dbType;

    @ApiModelProperty("所属系统对应的id")
    private String sysId;

    @ApiModelProperty("数据库端口")
    private String port;

    @ApiModelProperty("数据库连接状态,1正常，2失败")
    private String dbState;

    @ApiModelProperty("创建时间")
    private Date createTime;

}