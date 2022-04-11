package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

//网络设备的吞吐率
@ApiModel("网络设备状态实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetioState implements Serializable {

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("网络设备状态id")
    private String netioStateId;

    @ApiModelProperty("每秒钟接收的数据包,rxpck/s")
    private String rxpck;

    @ApiModelProperty("每秒钟发送的数据包,txpck/s")
    private String txpck;

    @ApiModelProperty("每秒钟接收的KB数,rxkB/s")
    private String rxbyt;

    @ApiModelProperty("秒钟发送的KB数,txkB/s")
    private String txbyt;

    @ApiModelProperty("秒钟接收的压缩数据包,rxcmp/s")
    private String rxcmp;

    @ApiModelProperty("每秒钟发送的压缩数据包,txcmp/s")
    private String txcmp;

    @ApiModelProperty("每秒钟接收的多播数据包,rxmcst/s")
    private String rxmcst;

    @ApiModelProperty("创建时间")
    private Date createTime;


}