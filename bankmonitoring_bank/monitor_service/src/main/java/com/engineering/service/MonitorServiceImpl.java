package com.engineering.service;

import com.alibaba.fastjson.JSON;
import com.engineering.dao.MonitorMapper;
import com.engineering.pojo.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@DubboService
@Service
public class MonitorServiceImpl implements MonitorService {

    //注入，对应加  @Repository
    @Autowired
    private MonitorMapper monitorMapper;


    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    //获取系统的所有数据
    @Override
    public String getSystemInfo() {
        ArrayList <SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        return JSON.toJSONString(allSystemInfo);
    }

    @Override
    public String getDbInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList <DbInfo> dbInfoByIdAndTime = monitorMapper.getDbInfoByIdAndTime(sysId,createTime);
        return JSON.toJSONString(dbInfoByIdAndTime);
    }

    @Override
    public String getLogInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList <LogInfo> LogInfoByIdAndTime = monitorMapper.getLogInfoByIdAndTime(sysId,createTime);
        return JSON.toJSONString(LogInfoByIdAndTime);
    }

    @Override
    public String getProcessInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList <ProcessInfo> processInfoByIdAndTime = monitorMapper.getProcessInfoByIdAndTime(sysId,createTime);
        return JSON.toJSONString(processInfoByIdAndTime);
    }

    @Override
    public String getHostInfoByIdAndTime(String sysId, Date createTime) {
        ArrayList <HostInfo> hostInfoByIdAndTime = monitorMapper.getHostInfoByIdAndTime(sysId,createTime);
        return JSON.toJSONString(hostInfoByIdAndTime);
    }

    @Override
    public String getHostSumByIdAndTime(String sysId, Date createTime){
        ArrayList <HostInfo> hostInfoByIdAndTime = monitorMapper.getHostInfoByIdAndTime(sysId,createTime);
        int count = 0;
        for(HostInfo host:hostInfoByIdAndTime){
            if (host.getHostState().contentEquals("1")){
                count += 1;
            }
        }
        return JSON.toJSONString(count)+"/"+JSON.toJSONString(hostInfoByIdAndTime.size());
    }

    @Override
    public String getProcessSumByIdAndTime(String sysId, Date createTime){
        ArrayList <ProcessInfo> processInfoByIdAndTime = monitorMapper.getProcessInfoByIdAndTime(sysId,createTime);
        int count = 0;
        for(ProcessInfo process:processInfoByIdAndTime){
            if (process.getProcessState().contentEquals("1")){ count += 1; }
        }
        return JSON.toJSONString(count)+"/"+JSON.toJSONString(processInfoByIdAndTime.size());
    }

    @Override
    public String getLogSumByIdAndTime(String sysId, Date createTime){
        ArrayList <LogInfo> logInfoByIdAndTime = monitorMapper.getLogInfoByIdAndTime(sysId,createTime);
        return JSON.toJSONString(logInfoByIdAndTime.size());
    }

    @Override
    public String getDbSumByIdAndTime(String sysId, Date createTime){
        ArrayList <DbInfo> dbInfoByIdAndTime = monitorMapper.getDbInfoByIdAndTime(sysId,createTime);
        int count = 0;
        for(DbInfo db:dbInfoByIdAndTime){
            if (db.getDbState().contentEquals("1")){ count += 1; }
        }
        return JSON.toJSONString(count)+"/"+JSON.toJSONString(dbInfoByIdAndTime.size());
    }


    @Override
    public String getDbInfoByTime(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存db
        ArrayList<DbInfo> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的db list
            ArrayList <DbInfo> dbInfoByIdAndTime = monitorMapper.getDbInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的db list，并加入新的list（用来保存全部）
            for (DbInfo dbInfo : dbInfoByIdAndTime) {
                res.add(dbInfo);
            }
        }
        return JSON.toJSONString(res);
    }

    @Override
    public String getLogInfoByTime(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存log
        ArrayList<LogInfo> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的log list
            ArrayList <LogInfo> logInfoByTime = monitorMapper.getLogInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的log list，并加入新的list（用来保存全部）
            for (LogInfo logInfo : logInfoByTime) {
                res.add(logInfo);
            }
        }
        return JSON.toJSONString(res);
    }

    @Override
    public String getProcessInfoByTime(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存process
        ArrayList<ProcessInfo> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的process list
            ArrayList <ProcessInfo> processInfoByTime = monitorMapper.getProcessInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的process list，并加入新的list（用来保存全部）
            for (ProcessInfo processInfo : processInfoByTime) {
                res.add(processInfo);
            }
        }
        return JSON.toJSONString(res);
    }

    @Override
    public String getHostInfoByTime(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存process
        ArrayList<HostInfo> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的process list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的process list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                res.add(hostInfo);
            }
        }
        return JSON.toJSONString(res);
    }


    //每台主机的详细数据
    @Override
    public String getDeskState(String hostId, Date createTime) {
        DeskState deskState = monitorMapper.getDeskStateByIdAndTime(hostId,createTime);
        return JSON.toJSONString(deskState);
    }

    @Override
    public String getNetioState(String hostId, Date createTime) {
        NetioState netioState = monitorMapper.getNetioStateByIdAndTime(hostId,createTime);
        return JSON.toJSONString(netioState );
    }

    @Override
    public String getTcpState(String hostId, Date createTime) {
        TcpState tcpState = monitorMapper.getTcpStateByIdAndTime(hostId,createTime);
        return JSON.toJSONString(tcpState );
    }

    @Override
    public String getCpuState(String hostId, Date createTime) {
        CpuState cpuState = monitorMapper.getCpuStateByIdAndTime(hostId,createTime);
        return JSON.toJSONString(cpuState );
    }

    @Override
    public String getMemState(String hostId, Date createTime) {
        MemState menState = monitorMapper.getMemStateByIdAndTime(hostId,createTime);
        return JSON.toJSONString(menState );
    }

    @Override
    public String getLoadState(String hostId, Date createTime) {
        LoadState loadState = monitorMapper.getLoadStateByIdAndTime(hostId, createTime);
        return JSON.toJSONString(loadState);
    }

    @Override
    public String getDeskStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<DeskState> alldesks = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                alldesks.add(monitorMapper.getDeskStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(alldesks);
    }

    @Override
    public String getNetioStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<NetioState> allNetios = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                allNetios.add(monitorMapper.getNetioStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(allNetios);
    }

    @Override
    public String getTcpStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<TcpState> allTcps = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                allTcps.add(monitorMapper.getTcpStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(allTcps);
    }

    @Override
    public String getCpuStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<CpuState> allCpus = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                allCpus.add(monitorMapper.getCpuStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(allCpus);
    }

    @Override
    public String getMemStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<MemState> allMems = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                allMems.add(monitorMapper.getMemStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(allMems);
    }

    @Override
    public String getLoadStateByTime(Date createTime) {
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        ArrayList<LoadState> allLoads = new ArrayList<>();
        for(SystemInfo system:allSystemInfo){
            ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),createTime);
            for(HostInfo host:hosts){
                allLoads.add(monitorMapper.getLoadStateByIdAndTime(host.getHostId(),createTime));
            }
        }
        return JSON.toJSONString(allLoads);
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     * @param beginDate
     * @param endDate
     * @return List<Date>
     * @throws ParseException
     */
    public static List<String> getDatesBetweenTwoDate(String beginDate, String endDate) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        List<String> lDate = new ArrayList<String>();
        lDate.add(beginDate);//把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        //使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(sdf.parse(beginDate));
        boolean bContinue = true;
        while (bContinue) {
            //根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.SECOND, 1);
            // 测试此日期是否在指定日期之后
            if (sdf.parse(endDate).after(cal.getTime())) {
                lDate.add(sdf.format(cal.getTime()));
            } else {
                break;
            }
        }
        lDate.add(endDate);//把结束时间加入集合
        return lDate;
    }



    //获取  某一时间段的  主机id 和 主机内存
    @Override
    //2021-06-25_14:00:00  2021-06-25_14:14:00
    public String getMemStateByTimeSeries(Date start, Date end) throws ParseException {
        HashMap<String, Object> map = new HashMap();
        ArrayList<SystemInfo> systems = monitorMapper.getAllSystemInfo();
        Long spread = end.getTime() - start.getTime();
        int num = (int) (spread / 60000);
        for (SystemInfo system : systems) {
            //highMem存储所有最大值
            ArrayList<String> highMem = new ArrayList();
            Long time = start.getTime();
            Date date = new Date();//每个时间点
            date.setTime(time);
            for (int i = 0; i < num; i++) {
                ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(), date);
                //allMem存储所有磁盘占用
                ArrayList<String> allMem = new ArrayList<>();
                //获取系统下所有主机的最大磁盘占用
                for (HostInfo host : hosts) {
                    allMem.add(monitorMapper.getMemStateByIdAndTime(host.getHostId(), date).getUsePer());
                }
                //判断最大
                while (allMem.size() > 1) {
                    if (Double.parseDouble(allMem.get(0)) > Double.parseDouble(allMem.get(1)))
                        allMem.remove(1);
                    else
                        allMem.remove(0);
                }
                highMem.add(allMem.get(0));
                date.setTime(date.getTime() + 60000);
            }
            map.put(system.getSysName(), highMem);
        }
        return JSON.toJSONString(map);
    }
    //获取  某一时间段的  主机id  和 主机磁盘
    @Override
    public String getDeskStateByTimeSeries(Date start, Date end) throws ParseException {
        //单个系统下所有主机的所有磁盘的最大占用
        HashMap<String,Object> map = new HashMap();
        ArrayList<SystemInfo> systems = monitorMapper.getAllSystemInfo();
        Long spread = end.getTime() - start.getTime();
        int num = (int)(spread / 60000);
        for(SystemInfo system:systems) {
            //highMem存储所有最大值
            ArrayList<String> highDesk = new ArrayList();
            Long time = start.getTime();
            Date date = new Date();//每个时间点
            date.setTime(time);for (int i = 0; i < num; i++) {
                ArrayList<HostInfo> hosts = monitorMapper.getHostInfoByIdAndTime(system.getSysId(),date);
                //allMem存储所有磁盘占用
                ArrayList<String> allDesk = new ArrayList<>();
                //获取系统下所有主机的最大磁盘占用
                for(HostInfo host:hosts){
                    allDesk.add(monitorMapper.getDeskStateByIdAndTime(host.getHostId(),date).getUsePer());
                }
                //判断最大
                while(allDesk.size()>1){
                    if(Double.parseDouble(allDesk.get(0)) > Double.parseDouble(allDesk.get(1)))
                        allDesk.remove(1);
                    else
                        allDesk.remove(0);
                }
                highDesk.add(allDesk.get(0));
                date.setTime(date.getTime() + 60000);
            }
            map.put(system.getSysName(),highDesk);
        }
        return JSON.toJSONString(map);
    }






    //告警模块
    //数据库告警   系统id，上线/下线dbState
    @Override
    public String getDbInfoForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存db
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的db list
            ArrayList <DbInfo> dbInfoByIdAndTime = monitorMapper.getDbInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的db list，并加入新的list（用来保存全部）
            for (DbInfo dbInfo : dbInfoByIdAndTime) {
                String [] dbInfoState = {dbInfo.getDbId(),dbInfo.getDbState()};
                res.add(dbInfoState);
            }
        }
        return JSON.toJSONString(res);
    }

    //所有系统的日志告警信息：系统id，创建成功/失败
    @Override
    public String getLogInfoForWarning(Date createTime) {
        ///得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存log
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的log list
            ArrayList <LogInfo> logInfoByTime = monitorMapper.getLogInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的log list，并加入新的list（用来保存全部）
            for (LogInfo logInfo : logInfoByTime) {
                String [] logInfoState = {logInfo.getLogId(),logInfo.getState()};
                res.add(logInfoState);
            }
        }
        return JSON.toJSONString(res);

    }

    //所有系统的进程告警信息：系统id，上线/下线
    @Override
    public String getProcessInfoForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存process
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的process list
            ArrayList <ProcessInfo> processInfoByTime = monitorMapper.getProcessInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的process list，并加入新的list（用来保存全部）
            for (ProcessInfo processInfo : processInfoByTime) {
                String [] processInfoState = {processInfo.getProcessId(),processInfo.getProcessState()};
                res.add(processInfoState);
            }
        }
        return JSON.toJSONString(res);

    }

    //所有系统的主机告警信息：系统id，上线/下线
    @Override
    public String getHostInfoForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存host
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的process list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的process list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                String [] hostInfoState = {hostInfo.getHostId(),hostInfo.getHostState()};
                res.add(hostInfoState);
            }
        }
        return JSON.toJSONString(res);

    }

    //所有主机的desk告警信息：主机id，使用百分比
    @Override
    public String getDeskStateForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存host
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的host list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的host list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                DeskState deskState = monitorMapper.getDeskStateByIdAndTime(hostInfo.getHostId(),createTime);
                String [] deskInfoState = {deskState.getHostId(),deskState.getUsePer()};
                res.add(deskInfoState);
            }
        }
        return JSON.toJSONString(res);

    }

    //所有主机的netio告警信息：暂时未知
    @Override
    public String getNetioStateForWarning(Date createTime) {
        return null;
    }

    //所有主机的tcp告警信息：暂时未知
    @Override
    public String gettTcpStateInfoForWarning(Date createTime) {
        return null;
    }

    //所有主机的cpu告警信息：主机id，cpu使用率
    @Override
    public String getCpuStateForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存host
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的host list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的host list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                CpuState cpuState = monitorMapper.getCpuStateByIdAndTime(hostInfo.getHostId(),createTime);
                String [] cpuInfoState = {cpuState.getHostId(),cpuState.getUsePer()};
                res.add(cpuInfoState);
            }
        }
        return JSON.toJSONString(res);

    }

    //所有主机的mem告警信息：主机id，使用百分比
    @Override
    public String getMemStateInfoForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存host
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的host list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的host list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                MemState menState = monitorMapper.getMemStateByIdAndTime(hostInfo.getHostId(),createTime);
                String [] menInfoState = {menState.getHostId(),menState.getUsePer()};
                res.add(menInfoState);
            }
        }
        return JSON.toJSONString(res);

    }



    //所有主机的load告警信息：主机id，1分钟，5分钟，15分钟
    @Override
    public String getLoadStateForWarning(Date createTime) {
        //得到所有系统的信息
        ArrayList<SystemInfo> allSystemInfo = monitorMapper.getAllSystemInfo();
        //存host
        ArrayList <String[]> res = new ArrayList<>();
        //遍历
        for (SystemInfo systemInfo : allSystemInfo) {
            //得到系统X的host list
            ArrayList <HostInfo> hostInfoByTime = monitorMapper.getHostInfoByIdAndTime(systemInfo.getSysId(),createTime);
            //遍历系统x的host list，并加入新的list（用来保存全部）
            for (HostInfo hostInfo : hostInfoByTime) {
                LoadState loadState = monitorMapper.getLoadStateByIdAndTime(hostInfo.getHostId(), createTime);
                //数组包含3个时间段的值
                String [] time_load = {loadState.getHostId(),loadState.getOneLoad(),loadState.getFiveLoad(),loadState.getFifteenLoad()};
                res.add(time_load);
            }
        }
        return JSON.toJSONString(res);

    }





}


