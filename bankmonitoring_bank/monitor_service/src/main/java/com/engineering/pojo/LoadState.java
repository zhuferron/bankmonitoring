package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//查看系统负载状态
@ApiModel("主机负载状态实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadState implements Serializable {

    @ApiModelProperty("负载状况Id")
    private String loadId;

    @ApiModelProperty("对应主机Id")
    private String hostId;

    @ApiModelProperty("1分钟之前到现在的负载")
    private String oneLoad;

    @ApiModelProperty("5分钟之前到现在的负载")
    private String fiveLoad;

    @ApiModelProperty("15分钟之前到现在的负载")
    private String fifteenLoad;

    @ApiModelProperty("创建时间")
    private Date createTime;

}

