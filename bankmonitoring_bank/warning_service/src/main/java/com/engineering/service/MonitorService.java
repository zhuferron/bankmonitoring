package com.engineering.service;

import java.util.Date;

public interface MonitorService {

    //home页面，获得所有系统的基本信息
    public String getSystemInfo();

    //每个系统的单独的大概信息
    public String getDbInfoByIdAndTime(String sysId, Date createTime);

    public String getLogInfoByIdAndTime(String sysId, Date createTime);

    public String getProcessInfoByIdAndTime(String sysId, Date createTime);

    public String getHostInfoByIdAndTime(String sysId, Date createTime);

    //返回系统概况
    public String getHostSumByIdAndTime(String sysId, Date createTime);

    public String getProcessSumByIdAndTime(String sysId, Date createTime);

    public String getLogSumByIdAndTime(String sysId, Date createTime);

    public String getDbSumByIdAndTime(String sysId, Date createTime);





    //各个系统模块的信息（全部）
    public String getDbInfoByTime(Date createTime);

    public String getLogInfoByTime(Date createTime);

    public String getProcessInfoByTime(Date createTime);

    public String getHostInfoByTime(Date createTime);



    //每台主机的详细数据
    public String getDeskState(String hostId, Date createTime);

    public String getNetioState(String hostId, Date createTime);

    public String getTcpState(String hostId, Date createTime);

    public String getCpuState(String hostId, Date createTime);

    public String getMemState(String hostId, Date createTime);

    public String getLoadState(String hostId, Date createTime);

    //告警模块接口
    //系统告警
    //返回指定时间的所有系统的数据库告警信息：系统id，上线/下线
    public String getDbInfoForWarning(Date createTime);
    //返回指定时间的所有系统的日志告警信息：系统id，创建成功/失败
    public String getLogInfoForWarning(Date createTime);
    //返回指定时间的所有系统的进程告警信息：系统id，上线/下线
    public String getProcessInfoForWarning(Date createTime);
    //返回指定时间的所有系统的主机告警信息：系统id，上线/下线
    public String getHostInfoForWarning(Date createTime);

    //主机告警
    //返回指定时间的所有主机的desk告警信息：主机id，使用百分比
    public String getDeskStateForWarning(Date createTime);
    //返回指定时间的所有主机的netio告警信息：暂时未知
    public String getNetioStateForWarning(Date createTime);
    //返回指定时间的所有主机的tcp告警信息：暂时未知
    public String gettTcpStateInfoForWarning(Date createTime);
    //返回指定时间的所有主机的cpu告警信息：主机id，cpu使用率
    public String getCpuStateForWarning(Date createTime);
    //返回指定时间的所有主机的mem告警信息：主机id，使用百分比
    public String getMemStateInfoForWarning(Date createTime);
    //返回指定时间的所有主机的load告警信息：主机id，1分钟，5分钟，15分钟
    public String getLoadStateForWarning(Date createTime);


}
