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
@ApiModel("主机信息实体类")
public class HostInfo implements Serializable {

    @ApiModelProperty("主机ip")
    private String ip;

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("主机名")
    private String hostName;

    @ApiModelProperty("主机状态，1正常，2下线")
    private String hostState;

    @ApiModelProperty("loadid")
    private String loadId;

    @ApiModelProperty("cpuid")
    private String cpuId;

    @ApiModelProperty("memid")
    private String memId;

    @ApiModelProperty("deskid")
    private String deskId;

    @ApiModelProperty("tcpid")
    private String tcpId;

    @ApiModelProperty("netioid")
    private String netioId;

    @ApiModelProperty("所属系统对应的id")
    private String sysId;

    @ApiModelProperty("创建时间")
    private Date createTime;
}