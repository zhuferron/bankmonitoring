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
@ApiModel("系统进程类")
public class ProcessInfo implements Serializable {

    @ApiModelProperty("进程ID")
    private String processId;

    @ApiModelProperty("进程名称")
    private String processName;

    @ApiModelProperty("进程状态")
    private String processState;

    @ApiModelProperty("进程Cpu占用百分比")
    private String processCpuOccupy;

    @ApiModelProperty("进程内存占用百分比")
    private String processMemOccupy;

    @ApiModelProperty("进程读取，M")
    private String processRead;

    @ApiModelProperty("进程写入，M")
    private String processWrite;

    @ApiModelProperty("所属系统对应的id")
    private String sysId;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
