package com.engineering.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//实体类
@ApiModel("密码实体类")
public class Password {

    private String id;

    private String oldPassword;

    private String newPassword;

}
