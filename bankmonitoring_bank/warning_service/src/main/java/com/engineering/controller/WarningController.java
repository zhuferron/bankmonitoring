package com.engineering.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.engineering.common.lang.Result;
import com.engineering.pojo.Email;
import com.engineering.pojo.StrongWarningForm;
import com.engineering.service.MonitorConsumer;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.monitor.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
public class WarningController {

    @Autowired
    private JavaMailSender mailSender;

    //@Autowired
    //private MonitorConsumer monitorConsumer;

    private String mailFrom = "2963624004@qq.com";

    @ApiOperation("弱告警模式")
    @RequestMapping(value = "/weakWarning",method = RequestMethod.POST)
    public String WeakWarning(@RequestBody Email email){
        try {
            System.out.println(email);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getText());
            mailMessage.setTo(email.getTo());
            mailMessage.setFrom(mailFrom);
            mailSender.send(mailMessage);
            return JSON.toJSONString(Result.succ("发送成功"));
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSONString(Result.fail("发送失败"));
        }
    }

    @ApiOperation("强告警模式")
    @RequestMapping(value = "/strongWarning",method = RequestMethod.POST)
    public String StrongWarning(@RequestBody StrongWarningForm strongWarningForm){
        try {
            //monitorConsumer.doStrongWarning(strongWarningForm);
            return JSON.toJSONString(Result.succ("发送成功"));
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSONString(Result.fail("发送失败"));
        }
    }




}
