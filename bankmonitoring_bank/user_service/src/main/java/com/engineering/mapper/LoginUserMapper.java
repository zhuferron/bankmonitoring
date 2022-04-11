package com.engineering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engineering.pojo.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface LoginUserMapper extends BaseMapper<LoginUser> {
}
