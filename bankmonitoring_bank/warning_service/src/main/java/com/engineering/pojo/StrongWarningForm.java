package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

//邮件实体类
@ApiModel("接收强告警表单的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrongWarningForm implements Serializable {

    @ApiModelProperty("监控间隔时间")
    private String time;
    @ApiModelProperty("是否开启数据库监控")
    private boolean dbState;
    @ApiModelProperty("是否开启日志监控")
    private boolean logState;
    @ApiModelProperty("是否开启进程监控")
    private boolean processState;
    @ApiModelProperty("是否开启主机监控")
    private boolean hostState;
    @ApiModelProperty("磁盘上下限")
    private ArrayList desk;
    @ApiModelProperty("磁cpu上下限")
    private ArrayList cpu;
    @ApiModelProperty("内存上下限")
    private ArrayList mem;
    @ApiModelProperty("1分钟负载上下限")
    private ArrayList oneLoad;
    @ApiModelProperty("5分钟负载上下限")
    private ArrayList fiveLoad;
    @ApiModelProperty("15分钟负载上下限")
    private ArrayList fifteenLoad;
    @ApiModelProperty("管理员邮箱")
    private String email;

}
