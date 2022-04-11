package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

//查看内存使用情况
@ApiModel("内存状态实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemState implements Serializable {

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("内存状态id")
    private String memStateId;

    @ApiModelProperty("总计内存，M")
    private String total;

    @ApiModelProperty("已使用多少，M")
    private String used;

    @ApiModelProperty("未使用，M")
    private String free;

    @ApiModelProperty("已使用百分比%")
    private String usePer;

    @ApiModelProperty("创建时间")
    private Date createTime;

}