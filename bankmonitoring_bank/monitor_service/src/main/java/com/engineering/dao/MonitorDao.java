package com.engineering.dao;

import com.engineering.pojo.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public interface MonitorDao {

    //获取全部系统
    public ArrayList<SystemInfo> getAllSystemInfo();

    public ArrayList<DbInfo> getDbInfoByIdAndTime(String sysId, Date createTime);

    public ArrayList<LogInfo> getLogInfoByIdAndTime(String sysId, Date createTime);

    public ArrayList<ProcessInfo> getProcessInfoByIdAndTime(String sysId, Date createTime);

    public ArrayList<HostInfo> getHostInfoByIdAndTime(String sysId, Date createTime);

    public MemState getMemStateByIdAndTime(String hostId, Date createTime);

    public DeskState getDeskStateByIdAndTime(String hostId, Date createTime);

    public NetioState getNetioStateByIdAndTime(String hostId, Date createTime);

    public TcpState getTcpStateByIdAndTime(String hostId, Date createTime);

    public CpuState getCpuStateByIdAndTime(String hostId, Date createTime);

    public LoadState getLoadStateByIdAndTime(String hostId, Date createTime);

}
