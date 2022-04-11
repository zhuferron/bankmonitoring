package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

//查看CPU使用情况
@ApiModel("Cpu状态实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuState implements Serializable {

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("Cpu状态id")
    private String cpuStateId;

    @ApiModelProperty("cpu个数")
    private String num;

    @ApiModelProperty("cpu速度")
    private String speed;

    @ApiModelProperty("cpu使用率")
    private String usePer;

    @ApiModelProperty("当前空闲率")
    private String idle;

    @ApiModelProperty("cpu当前等待率")
    private String iowait;

    @ApiModelProperty("创建时间")
    private Date createTime;

}