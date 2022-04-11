package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

//查看TCP连接状态
@ApiModel("TCP连接实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcpState implements Serializable {

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("TCP连接id")
    private String tcpStateId;

    @ApiModelProperty("每秒本地发起的TCP连接数，既通过connect调用创建的TCP连接；,active/s")
    private String active;

    @ApiModelProperty("每秒远程发起的TCP连接数，即通过accept调用创建的TCP连接,passive/s")
    private String passive;

    @ApiModelProperty("秒TCP重传数量,retrans/s")
    private String retrans;

    @ApiModelProperty("创建时间")
    private Date createTime;

}