package com.engineering.dao;

import com.alibaba.fastjson.JSONObject;
import com.engineering.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class MonitorMapper implements MonitorDao {

    @Autowired
    private RedisTemplate redisTemplate;

    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    @Override
    public ArrayList<SystemInfo> getAllSystemInfo() {
        //返回的是ArrayList<SystemInfo>

        ArrayList<SystemInfo> re = new ArrayList<>();
        ValueOperations ops = redisTemplate.opsForValue();
        ArrayList<String> sysIds = JSONObject.parseObject((String) ops.get("SystemIds"), ArrayList.class);
        System.out.println(sysIds);
        for (String sysId : sysIds) {
            re.add(JSONObject.parseObject((String) ops.get("System:" + sysId), SystemInfo.class));
        }
        return re;
    }

    //根据系统id和时间查询数据库
    @Override
    public ArrayList<DbInfo> getDbInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList<DbInfo> dbList = new ArrayList<>();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id和创建时间的sys，找到对应数据库id,然后根据数据库id查询数据库，添加到结果中
        SystemInfo system = JSONObject.parseObject((String) ops.get("System:"+sysId+":"+sdf.format(createTime)),SystemInfo.class);
        for (String dbId:system.getDbIds()) {
            dbList.add(JSONObject.parseObject((String) ops.get("Database:"+dbId+":"+sdf.format(createTime)),DbInfo.class));
        }
        return dbList;

    }

    @Override
    public ArrayList<LogInfo> getLogInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList<LogInfo> logList = new ArrayList<>();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id和时间的sys，找到对应日志id,然后根据日志id查询日志，添加到结果中
        SystemInfo system = JSONObject.parseObject((String) ops.get("System:"+sysId+":"+sdf.format(createTime)),SystemInfo.class);
        for (String logId:system.getLogInfoIds()) {
            logList.add(JSONObject.parseObject((String) ops.get("LogInfo:"+logId+":"+sdf.format(createTime)),LogInfo.class));
        }
        return logList;

    }

    @Override
    public ArrayList<ProcessInfo> getProcessInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList<ProcessInfo> processList = new ArrayList<>();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id和创建时间的sys，找到对应模块id,然后根据id和创建时间查询，添加到结果中
        SystemInfo system = JSONObject.parseObject((String) ops.get("System:"+sysId+":"+sdf.format(createTime)),SystemInfo.class);
        for (String processId:system.getProcessIds()) {
            processList.add(JSONObject.parseObject((String) ops.get("Process:"+processId+":"+sdf.format(createTime)),ProcessInfo.class));
        }
        return processList;
    }

    @Override
    public ArrayList<HostInfo> getHostInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList<HostInfo> hostList = new ArrayList<>();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id和创建时间的sys，找到对应模块id,然后根据id和创建时间查询，添加到结果中
        SystemInfo system = JSONObject.parseObject((String) ops.get("System:"+sysId+":"+sdf.format(createTime)),SystemInfo.class);
        for (String hostId:system.getHostIds()) {
            hostList.add(JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class));
        }
        return hostList;
    }

    //主机的实体类
    @Override
    public MemState getMemStateByIdAndTime(String hostId, Date createTime) {
        MemState memState = new MemState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        memState = JSONObject.parseObject((String) ops.get("MemState:"+host.getMemId()+":"+sdf.format(createTime)),MemState.class);
        return memState;
    }

    @Override
    public DeskState getDeskStateByIdAndTime(String hostId, Date createTime) {
        DeskState deskState = new DeskState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        deskState = JSONObject.parseObject((String) ops.get("DeskState:"+host.getDeskId()+":"+sdf.format(createTime)),DeskState.class);
        return deskState;
    }

    @Override
    public NetioState getNetioStateByIdAndTime(String hostId, Date createTime) {
        NetioState netioState = new NetioState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        netioState = JSONObject.parseObject((String) ops.get("NetioState:"+host.getNetioId()+":"+sdf.format(createTime)),NetioState.class);
        return netioState;
    }

    @Override
    public TcpState getTcpStateByIdAndTime(String hostId, Date createTime) {
        TcpState tcpState = new TcpState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        tcpState = JSONObject.parseObject((String) ops.get("TcpState:"+host.getTcpId()+":"+sdf.format(createTime)),TcpState.class);
        return tcpState;
    }

    @Override
    public CpuState getCpuStateByIdAndTime(String hostId, Date createTime) {
        CpuState cpuState = new CpuState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        cpuState = JSONObject.parseObject((String) ops.get("CpuState:"+host.getCpuId()+":"+sdf.format(createTime)),CpuState.class);
        return cpuState;
    }

    @Override
    public LoadState getLoadStateByIdAndTime(String hostId, Date createTime){
        LoadState loadState = new LoadState();
        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id的主机，获取主机的该模块的id，然后根据id和创建时间查询
        HostInfo host = JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(createTime)),HostInfo.class);
        loadState = JSONObject.parseObject((String) ops.get("LoadState:"+host.getLoadId()+":"+sdf.format(createTime)),LoadState.class);
        return loadState;
    }





//    @Override
//    public ArrayList<SysLoadInfo> getSysLoadInfoByTime(Date createTime) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<DbInfo> getDbInfoByTime(Date createTime) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<LogInfo> getLogInfoByTime(Date createTime) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<ProcessInfo> getProcessInfoByTime(Date createTime) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<HostInfo> getHostInfoByTime( Date createTime) {
//        return null;
//    }
}
