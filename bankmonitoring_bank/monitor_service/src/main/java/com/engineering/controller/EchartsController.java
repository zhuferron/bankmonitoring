package com.engineering.controller;

import com.alibaba.fastjson.JSON;
import com.engineering.common.lang.Result;
import com.engineering.service.MonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class EchartsController {

    @Autowired
    private MonitorServiceImpl monitorServiceImpl;

    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");


    @GetMapping("getEchart1Data")
    public String getEchart1Data(){

        ArrayList res = new ArrayList();

        for(int i=1; i<=10; i++){
            for(int j=1; j<=3; j++){
                HashMap<String, String> map = new HashMap<>();
                map.put("name","系统"+i+"主机"+j);
                map.put("value",new Random().nextInt(500)+"");
                res.add(map);
            }
        }
        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }

    @GetMapping("getEchart2Data")
    public String getEchart2Data(){

        HashMap<String, Map> res = new HashMap<>();


        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }

    @GetMapping("getEchart3Data")
    public String getEchart3Data(){

        ArrayList res = new ArrayList();
        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }

    @GetMapping("getEchart4Data")
    public String getEchart4Data(){

        ArrayList res = new ArrayList();

        for(int i=1; i<=10; i++){
            for(int j=1; j<=3; j++){
                HashMap<String, String> map = new HashMap<>();
                map.put("name","系统"+i+"主机"+j);
                map.put("value",new Random().nextInt(500)+"");
                res.add(map);
            }
        }
        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }


    @GetMapping("getEchart5Data")
    public String getEchart5Data(){

        ArrayList res = new ArrayList();

        for(int i=1; i<=10; i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name","系统"+i);
            int a = new Random().nextInt(3);
            map.put("online",a);
            map.put("offline",3-a);
            res.add(map);
        }
        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }

    @GetMapping("getEchart6Data")
    public String getEchart6Data(){

        HashMap<String, Map> res = new HashMap<>();


        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }


    @GetMapping("getTimeExceptionData")
    public String getTimeExceptionData(){
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        for(int i=0;i<7;i++){
            for(int j=0;j<24;j++){
                ArrayList<Integer> data = new ArrayList<>();
                data.add(i);
                data.add(j);
                data.add(new Random().nextInt(15));
                res.add(data);
            }
        }
        return JSON.toJSONString(Result.succ("获取数据成功",res));
    }




}
