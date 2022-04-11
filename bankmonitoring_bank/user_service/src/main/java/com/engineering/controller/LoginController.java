package com.engineering.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.engineering.common.lang.RandomID;
import com.engineering.common.lang.Result;
import com.engineering.mapper.LoginUserMapper;
import com.engineering.pojo.LoginUser;
import com.engineering.pojo.Password;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginUserMapper loginUserMapper;

    @GetMapping("/test")
    public String test(){
        return "连接成功";
    }


    @ApiOperation("用户登录请求")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody LoginUser loginUser){

        QueryWrapper<LoginUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",loginUser.getUsername());
        wrapper.eq("password",loginUser.getPassword());
        LoginUser user = loginUserMapper.selectOne(wrapper);

        if(user != null){
            HashMap<String, String> res = new HashMap<>();
            res.put("id",user.getId());
            res.put("username",user.getUsername());
            return JSON.toJSONString(Result.succ("登录成功",res));
        }else{
            return JSON.toJSONString(Result.fail("登录失败"));
        }
    }

    @ApiOperation("用户查询请求")
    @RequestMapping(value = "/getInfo/{id}",method = RequestMethod.GET)
    public String getInfo(@PathVariable(name = "id") String id){

        QueryWrapper<LoginUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        LoginUser user = loginUserMapper.selectOne(wrapper);
        if(user != null){
            return JSON.toJSONString(Result.succ("查询成功",user));
        }else{
            return JSON.toJSONString(Result.fail("查询失败"));
        }
    }

    @ApiOperation("用户信息更新")
    @RequestMapping(value = "/resetInfo",method = RequestMethod.POST)
    public String resetInfo(@RequestBody LoginUser loginUser){

        QueryWrapper<LoginUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",loginUser.getId());
        LoginUser user = loginUserMapper.selectOne(wrapper);
        if(user != null){
            LoginUser loginUser_resetloginUser = new LoginUser();
            loginUser_resetloginUser.setId(loginUser.getId());
            loginUser_resetloginUser.setEmail(loginUser.getEmail());
            //loginUser_resetloginUser.setRole(loginUser.getRole());
            loginUser_resetloginUser.setUsername(loginUser.getUsername());

            int i = loginUserMapper.updateById(loginUser_resetloginUser);
            if(i>0) return JSON.toJSONString(Result.succ("修改成功"));
            else return JSON.toJSONString(Result.fail("修改失败"));
        }else{
            return JSON.toJSONString(Result.fail("存在错误"));
        }
    }

    @ApiOperation("用户修改密码请求")
    @RequestMapping(value = "/resetPass",method = RequestMethod.POST)
    public String resetPass(@RequestBody Password password){

        QueryWrapper<LoginUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",password.getId());
        wrapper.eq("password",password.getOldPassword());
        LoginUser user = loginUserMapper.selectOne(wrapper);
        if(user != null){
            LoginUser loginUser_resetPassword = new LoginUser();
            loginUser_resetPassword.setId(password.getId());
            loginUser_resetPassword.setPassword(password.getNewPassword());
            int i = loginUserMapper.updateById(loginUser_resetPassword);
            if(i>0) return JSON.toJSONString(Result.succ("修改成功"));
            else return JSON.toJSONString(Result.fail("修改失败"));
        }else{
            return JSON.toJSONString(Result.fail("输入密码错误或id不存在"));
        }
    }







}

