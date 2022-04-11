package com.engineering.controller;

import com.alibaba.fastjson.JSON;

import com.engineering.common.lang.Result;
import com.engineering.pojo.LogInfo;
import com.engineering.service.MonitorService;
import com.engineering.service.MonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AllInfoController {

    @Autowired
    private MonitorServiceImpl monitorServiceImpl;

    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");


    //接口1：获取全部系统的名称和基础信息
    @GetMapping("/getAllSystemInfo")
    public String getAllSystemInfo(){
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getSystemInfo()));
    }

    //接口3：获取指定系统id和时间的   详细主机状态
    @GetMapping("getSystemHostInfoByIdAndTime/{systemId}/{time}")
    public String getSystemHostInfoByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                               @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getHostInfoByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口4：获取指定系统id和时间的   详细系统进程
    @GetMapping("getSystemProcessInfoByIdAndTime/{systemId}/{time}")
    public String getSystemProcessInfoByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                               @PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getProcessInfoByIdAndTime(systemId,sdf.parse(time))));
    }

    //接口5：获取指定系统id和时间的    详细系统日志
    @GetMapping("getSystemLogInfoByIdAndTime/{systemId}/{time}")
    public String getSystemLogInfoByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                                  @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getLogInfoByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口6：获取指定系统id和时间的     详细系统数据库信息
    @GetMapping("getSystemDbInfoByIdAndTime/{systemId}/{time}")
    public String getSystemDbInfoByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                              @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDbInfoByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口7：获取指定系统id和时间的主机数量和在线数
    @GetMapping("getHostSumByIdAndTime/{systemId}/{time}")
    public String getHostSumByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                              @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getHostSumByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口8：获取指定系统id和时间的进程数量和在线数
    @GetMapping("getProcessSumByIdAndTime/{systemId}/{time}")
    public String getProcessSumByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                        @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getProcessSumByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口9：获取指定系统id和时间的数据库数量和在线数
    @GetMapping("getDbSumByIdAndTime/{systemId}/{time}")
    public String getDbSumByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                        @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDbSumByIdAndTime(systemId, sdf.parse(time))));
    }

    //接口7：获取指定系统id和时间的日志数量
    @GetMapping("getLogSumByIdAndTime/{systemId}/{time}")
    public String getLogSumByIdAndTime(@PathVariable(value = "systemId") String systemId,
                                        @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getLogSumByIdAndTime(systemId, sdf.parse(time))));
    }


    //主机详细
    //接口：获取指定主机id和时间的    内存状况
    @GetMapping("getMemStateByIdAndTime/{hostId}/{time}")
    public String getMemStateByIdAndTime(@PathVariable(value = "hostId") String hostId,
                                             @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getMemState(hostId,sdf.parse(time))));
    }

    //接口：获取指定主机id和时间的   磁盘状态
    @GetMapping("getDeskStateByIdAndTime/{hostId}/{time}")
    public String getDeskStateByIdAndTime(@PathVariable(value = "hostId") String hostId,
                                         @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDeskState(hostId,sdf.parse(time))));
    }

    //接口：获取指定主机id和时间的  网络设备状态
    @GetMapping("getNetioStateByIdAndTime/{hostId}/{time}")
    public String getNetioStateByIdAndTime(@PathVariable(value = "hostId") String hostId,
                                          @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getNetioState(hostId,sdf.parse(time))));
    }

    //接口：获取指定主机id和时间的  TCP连接
    @GetMapping("getTcpStateByIdAndTime/{hostId}/{time}")
    public String getTcpStateByIdAndTime(@PathVariable(value = "hostId") String hostId,
                                           @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getTcpState(hostId,sdf.parse(time))));
    }

    //接口：获取指定主机id和时间的  Cpu状态
    @GetMapping("getCpuStateByIdAndTime/{hostId}/{time}")
    public String getCpuStateByIdAndTime(@PathVariable(value = "hostId") String hostId,
                                         @PathVariable(value = "time") String time) throws ParseException{
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getCpuState(hostId,sdf.parse(time))));
    }



    //模块接口   (针对所有系统的)
    //接口：获取  所有系统的指定时间的   详细系统负载信息
    @GetMapping("getSystemLoadInfoByTime/{time}")
    public String getSystemLoadInfoByTime(@PathVariable(value = "time") String time) throws ParseException {
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getLogInfoByTime(sdf.parse(time))));
    }

    //接口：获取  所有系统的指定时间的  详细主机状态
    @GetMapping("getSystemHostInfoByTime/{time}")
    public String getSystemHostInfoByTime(@PathVariable(value = "time") String time) throws ParseException {
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getHostInfoByTime(sdf.parse(time))));
    }


    //接口：获取  所有系统的指定时间的  详细系统进程
    @GetMapping("getSystemProcessInfoByTime/{time}")
    public String getSystemProcessInfoByTime(@PathVariable(value = "time") String time) throws ParseException {
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getProcessInfoByTime(sdf.parse(time))));
    }

    //接口：获取  所有系统的指定时间的  系统日志
    @GetMapping("getSystemLogInfoByTime/{time}")
    public String getSystemLogInfoByTime(@PathVariable(value = "time") String time) throws ParseException {
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getLogInfoByTime(sdf.parse(time))));
    }

    //接口：获取  所有系统的指定时间的  系统数据库信息
    @GetMapping("getSystemDbInfoByTime/{time}")
    public String getSystemDbInfoByTime(@PathVariable(value = "time") String time) throws ParseException {
        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDbInfoByTime(sdf.parse(time))));
    }


    //获取  某一时间段的  主机id 和 主机内存
    @GetMapping("getMemStateByTimeSeries/{hostId}/{startTime}/{endTime}")
    public String getMemStateByTimeSeries(@PathVariable(value = "hostId") String hostId,
                                          @PathVariable(value = "startTime") String startTime,
                                          @PathVariable(value = "endTime") String endTime) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getMemStateByTimeSeries(sdf.parse(startTime),sdf.parse(endTime))));
    }

    //获取  某一时间段的  主机id  和 主机磁盘
    @GetMapping("getDeskStateByTimeSeries/{hostId}/{startTime}/{endTime}")
    public String getDeskStateByTimeSeries(@PathVariable(value = "hostId") String hostId,
                                          @PathVariable(value = "startTime") String startTime,
                                          @PathVariable(value = "endTime") String endTime) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDeskStateByTimeSeries(sdf.parse(startTime),sdf.parse(endTime))));
    }

    //接口：根据时间获取所有主机的磁盘状态
    @GetMapping("getDeskStateByTime/{time}")
    public String getDeskStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getDeskStateByTime(sdf.parse(time))));
    }

    //接口：根据时间获取所有主机的内存状态
    @GetMapping("getMemStateByTime/{time}")
    public String getMemStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getMemStateByTime(sdf.parse(time))));
    }

    //接口：根据时间获取所有主机的Net设备状态
    @GetMapping("getNetioStateByTime/{time}")
    public String getNetioStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getNetioStateByTime(sdf.parse(time))));
    }

    //接口：根据时间获取所有主机的Tcp状态
    @GetMapping("getTcpStateByTime/{time}")
    public String getTcpStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getTcpStateByTime(sdf.parse(time))));
    }

    //接口：根据时间获取所有主机的磁盘状态
    @GetMapping("getCpuStateByTime/{time}")
    public String getCpuStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getCpuStateByTime(sdf.parse(time))));
    }

    //接口：根据时间获取所有主机的磁盘状态
    @GetMapping("getLoadStateByTime/{time}")
    public String getLoadStateByTime(@PathVariable(value = "time") String time) throws ParseException {

        return JSON.toJSONString(Result.succ(
                "查询成功",
                monitorServiceImpl.getLoadStateByTime(sdf.parse(time))));
    }



}
