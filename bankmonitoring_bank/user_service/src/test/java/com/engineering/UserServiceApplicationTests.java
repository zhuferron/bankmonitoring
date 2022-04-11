package com.engineering;

import com.engineering.common.lang.RandomID;
import com.engineering.mapper.LoginUserMapper;
import com.engineering.pojo.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    LoginUserMapper loginUserMapper;

    @Test
    void contextLoads() {

        LoginUser new_user = new LoginUser();
        new_user.setId(RandomID.getUUID());
        new_user.setUsername("ferron");
        new_user.setPassword("sanfashi");
        new_user.setRole("超级管理员");
        new_user.setEmail("2963624004@qq.ocm");
        int i = loginUserMapper.insert(new_user);
        if(i>0) System.out.println("成功");

    }

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void testRedis(){

        LoginUser new_user = new LoginUser();
        new_user.setId(RandomID.getUUID());
        new_user.setUsername("ferron");
        new_user.setPassword("sanfashi");
        new_user.setRole("超级管理员");
        new_user.setEmail("2963624004@qq.ocm");

        redisTemplate.opsForValue().set("ferron",new_user);
        LoginUser ferron = (LoginUser) redisTemplate.opsForValue().get("ferron");
        System.out.println(ferron.toString());


    }

}
