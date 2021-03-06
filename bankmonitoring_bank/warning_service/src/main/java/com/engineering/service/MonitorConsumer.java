package com.engineering.service;

import com.alibaba.fastjson.JSONObject;
import com.engineering.common.lang.Result;
import com.engineering.pojo.StrongWarningForm;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
public class MonitorConsumer {

    @DubboReference
    MonitorService monitorService;

    @Autowired
    private JavaMailSender mailSender;

    private String mailFrom = "2963624004@qq.com";
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    StrongWarningForm strongWarningForm=new StrongWarningForm("5",true,false,false,false,new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),"hollehere@163.com");
    final String a="5";

    public void change(StrongWarningForm strongWarningForm){
        this.strongWarningForm=strongWarningForm;
    }

    @Scheduled(cron = "*/"+a+" * * * * ?" )
    public void doStrongWarning() throws ParseException {
        this.doLog_StrongWarning(strongWarningForm);
        this.doProcess_StrongWarning(strongWarningForm);
        this.doHost_StrongWarning(strongWarningForm);
        this.doDatabase_StrongWarning(strongWarningForm);
        this.doHostWithDesk_StrongWarning(strongWarningForm);
        this.doHostWithCpu_StrongWarning(strongWarningForm);
        this.doHostWithMen_StrongWarning(strongWarningForm);
        this.doHostWithLoad_StrongWarning(strongWarningForm);
    };

    public void doLog_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isLogState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getLogInfoForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                    if(strings[1] == "??????"){
                        this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"????????????","system");
                    }
            }
        }
    }

    public void doProcess_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isProcessState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getProcessInfoForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(strings[1] == "??????"){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","system");
                }
            }
        }
    }

    public void doHost_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isHostState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDbInfoForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(strings[1] == "??????"){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","system");
                }
            }
        }
    }

    public void doDatabase_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isDbState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDbInfoForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object string : arrayList) {
                ArrayList list = JSONObject.parseObject(string.toString(), ArrayList.class);
                System.out.println();
                System.out.println();
                if(list.get(1).toString().equals("1")){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),list.get(0).toString(),"?????????","system");
                    System.out.println("????????????");
                }
            }
        }
    }

    public void doHostWithDesk_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isHostState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDeskStateForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(Double.parseDouble(strings[1])>Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostup");
                }else if(Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostdown");
                }
            }
        }
    }

    public void doHostWithCpu_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isHostState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDeskStateForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(Double.parseDouble(strings[1])>Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"Cpu","hostup");
                }else if(Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"Cpu","hostdown");
                }
            }
        }
    }

    public void doHostWithMen_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isHostState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDeskStateForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(Double.parseDouble(strings[1])>Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostup");
                }else if(Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getDesk().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostdown");
                }
            }
        }
    }

    public void doHostWithLoad_StrongWarning(StrongWarningForm strongWarningForm) throws ParseException {
        if(strongWarningForm.isHostState()){
            ArrayList arrayList = JSONObject.parseObject(monitorService.getDeskStateForWarning(sdf.parse("2021-05-18 00:00:00")), ArrayList.class);
            for (Object o : arrayList) {
                String[] strings = JSONObject.parseObject((String) o, String[].class);
                if(Double.parseDouble(strings[1])>Double.parseDouble(strongWarningForm.getOneLoad().get(1).toString()) || Double.parseDouble(strings[2])>Double.parseDouble(strongWarningForm.getFiveLoad().get(1).toString()) ||Double.parseDouble(strings[3])>Double.parseDouble(strongWarningForm.getFifteenLoad().get(1).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostup");
                }else if(Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getOneLoad().get(0).toString()) || Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getFiveLoad().get(0).toString()) ||Double.parseDouble(strings[1])<Double.parseDouble(strongWarningForm.getFifteenLoad().get(0).toString())){
                    this.doStrongWarning_Email(strongWarningForm.getEmail(),strings[0],"??????","hostdown");
                }
            }
        }
    }

    public void doStrongWarning_Email(String email,String id, String type, String judge){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("????????????????????????");
        if(judge=="system") mailMessage.setText("????????????????????????????????????id???"+id+"???"+type+"?????????????????????????????????");
        else if(judge=="hostup") mailMessage.setText("??????????????????????????????????????????id???"+id+"???"+type+"?????????????????????????????????");
        else if(judge=="hostdown") mailMessage.setText("??????????????????????????????????????????id???"+id+"???"+type+"????????????????????????????????????");
        mailMessage.setTo(email);
        mailMessage.setFrom(mailFrom);
        mailSender.send(mailMessage);
    }
}
