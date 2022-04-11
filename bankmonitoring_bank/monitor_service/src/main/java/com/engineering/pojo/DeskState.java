package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;


//查看磁盘大小使用信息
@ApiModel("磁盘状态实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeskState implements Serializable {

    @ApiModelProperty("主机id")
    private String hostId;

    @ApiModelProperty("磁盘状态id")
    private String deskStateId;

    @ApiModelProperty("盘符类型")
    private String fileSystem;

    @ApiModelProperty("分区大小")
    private String size;

    @ApiModelProperty("已使用")
    private String used;

    @ApiModelProperty("可用")
    private String avail;

    @ApiModelProperty("使用百分比")
    private String usePer;

    @ApiModelProperty("创建时间")
    private Date createTime;

}