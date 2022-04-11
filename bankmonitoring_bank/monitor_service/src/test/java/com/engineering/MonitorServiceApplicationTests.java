package com.engineering;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.engineering.common.lang.RandomID;
import com.engineering.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.yaml.snakeyaml.parser.ParserException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MonitorServiceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    @Test
    void contextLoads() throws ParseException {
        ValueOperations ops = redisTemplate.opsForValue();
//        创建系统基本信息
        Random r = new Random();
//      第一个时间：2021-06-25,第一个系统
        Date date = sdf.parse("2021-06-25_14:00:00");
        SystemInfo system1 = new SystemInfo(new RandomID().getUUID(), "核心业务系统", "5.2.1", generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system2 = new SystemInfo(new RandomID().getUUID(),"国际清算系统","4.9.8",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system3 = new SystemInfo(new RandomID().getUUID(),"信用卡系统","4.2.3",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system4 = new SystemInfo(new RandomID().getUUID(),"债券交易系统","3.9.2",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system5 = new SystemInfo(new RandomID().getUUID(),"信贷管理系统","5.4.1",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system6 = new SystemInfo(new RandomID().getUUID(),"财务管理系统","3.3.1",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system7 = new SystemInfo(new RandomID().getUUID(),"保理业务系统","4.4.1",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system8 = new SystemInfo(new RandomID().getUUID(),"银行IC卡系统","2.5.2",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system9 = new SystemInfo(new RandomID().getUUID(),"基金托管系统","3.4.1",generateID2(), generateID3(), generateID2(), generateID2(), date);
        SystemInfo system10 = new SystemInfo(new RandomID().getUUID(),"管理会计系统","3.1.1",generateID2(), generateID3(), generateID2(), generateID2(), date);

        //存入系统基础信息
        ArrayList<String> sysIds = new ArrayList<>();
        sysIds.add(system1.getSysId());
        sysIds.add(system2.getSysId());
        sysIds.add(system3.getSysId());
        sysIds.add(system4.getSysId());
        sysIds.add(system5.getSysId());
        sysIds.add(system6.getSysId());
        sysIds.add(system7.getSysId());
        sysIds.add(system8.getSysId());
        sysIds.add(system9.getSysId());
        sysIds.add(system10.getSysId());

        ops.set("SystemIds",JSON.toJSONString(sysIds));
        System.out.println(sysIds);

        ops.set("System:"+system1.getSysId(),JSON.toJSONString(system1));
        ops.set("System:"+system2.getSysId(),JSON.toJSONString(system2));
        ops.set("System:"+system3.getSysId(),JSON.toJSONString(system3));
        ops.set("System:"+system4.getSysId(),JSON.toJSONString(system4));
        ops.set("System:"+system5.getSysId(),JSON.toJSONString(system5));
        ops.set("System:"+system6.getSysId(),JSON.toJSONString(system6));
        ops.set("System:"+system7.getSysId(),JSON.toJSONString(system7));
        ops.set("System:"+system8.getSysId(),JSON.toJSONString(system8));
        ops.set("System:"+system9.getSysId(),JSON.toJSONString(system9));
        ops.set("System:"+system10.getSysId(),JSON.toJSONString(system10));

        ArrayList<SystemInfo> sys = new ArrayList<>();
        sys.add(system1);
        sys.add(system2);
        sys.add(system3);
        sys.add(system4);
        sys.add(system5);
        sys.add(system6);
        sys.add(system7);
        sys.add(system8);
        sys.add(system9);
        sys.add(system10);
        System.out.println(sys);

//     录入系统信息

        for(SystemInfo system : sys) {
            //固定主机下面的各类的id
            HostInfo host11 = new HostInfo(r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255), system.getHostIds().get(0), "主机1", giveState() + "", new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(),
                    new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(), system.getSysId(), date);
            HostInfo host12 = new HostInfo(r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255), system.getHostIds().get(1), "主机2", giveState() + "", new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(),
                    new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(), system.getSysId(), date);
            HostInfo host13 = new HostInfo(r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255), system.getHostIds().get(2), "主机3", giveState() + "", new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(),
                    new RandomID().getUUID(), new RandomID().getUUID(), new RandomID().getUUID(), system.getSysId(), date);

            date = sdf.parse("2021-06-25_14:00:00");
            for (int i = 0; i < 900; i++) {
                host11.setHostState(giveState()+"");
                host12.setHostState(giveState()+"");
                host13.setHostState(giveState()+"");
                ops.set("Host:" + host11.getHostId() + ":" + sdf.format(date), JSONObject.toJSONString(host11));
                ops.set("Host:" + host12.getHostId() + ":" + sdf.format(date), JSONObject.toJSONString(host12));
                ops.set("Host:" + host13.getHostId() + ":" + sdf.format(date), JSONObject.toJSONString(host13));
                //创建数据库信息
                DbInfo db11 = new DbInfo(system.getDbIds().get(0), "客户信息", "Redis", system.getSysId(), "6379", giveState() + "", date);
                DbInfo db12 = new DbInfo(system.getDbIds().get(1), "存款业务", "MySQL", system.getSysId(), "3306", giveState() + "", date);

                ops.set("Database:" + db11.getDbId() + ":" + sdf.format(date), JSON.toJSONString(db11));
                ops.set("Database:" + db12.getDbId() + ":" + sdf.format(date), JSON.toJSONString(db12));
//
//        //主机的相关类
//        //负载
                LoadState load11 = new LoadState(host11.getLoadId(), host11.getHostId(), r.nextInt(4)+"", 2+r.nextInt(4)+"", 5+r.nextInt(15)+"", date);//主机1
                LoadState load12 = new LoadState(host12.getLoadId(), host12.getHostId(), r.nextInt(4)+"", 2+r.nextInt(4)+"", 5+r.nextInt(15)+"", date);//主机2
                LoadState load13 = new LoadState(host13.getLoadId(), host13.getHostId(), r.nextInt(4)+"", 2+r.nextInt(4)+"", 5+r.nextInt(15)+"", date);//主机3

                ops.set("LoadState:" + load11.getLoadId() + ":" + sdf.format(date), JSONObject.toJSONString(load11));
                ops.set("LoadState:" + load12.getLoadId() + ":" + sdf.format(date), JSONObject.toJSONString(load12));
                ops.set("LoadState:" + load13.getLoadId() + ":" + sdf.format(date), JSONObject.toJSONString(load13));

//        CPU
                CpuState cpuState11 = new CpuState(host11.getHostId(), host11.getCpuId(), "2", String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 100), String.format("%.2f", r.nextDouble() * 100), String.format("%.2f", r.nextDouble() * 100), date);
                CpuState cpuState12 = new CpuState(host12.getHostId(), host12.getCpuId(), "4", String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 100), String.format("%.2f", r.nextDouble() * 100), date);
                CpuState cpuState13 = new CpuState(host13.getHostId(), host13.getCpuId(), "4", String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 100), String.format("%.2f", r.nextDouble() * 100), date);

                ops.set("CpuState:" + cpuState11.getCpuStateId() + ":" + sdf.format(date), JSONObject.toJSONString(cpuState11));
                ops.set("CpuState:" + cpuState12.getCpuStateId() + ":" + sdf.format(date), JSONObject.toJSONString(cpuState12));
                ops.set("CpuState:" + cpuState13.getCpuStateId() + ":" + sdf.format(date), JSONObject.toJSONString(cpuState12));
//
////        MemState
                MemState memState11 = new MemState(host11.getHostId(), host11.getMemId(), "", "", "", "", date);
                MemState memState12 = new MemState(host12.getHostId(), host12.getMemId(), "", "", "", "", date);
                MemState memState13 = new MemState(host13.getHostId(), host13.getMemId(), "", "", "", "", date);

                int total1 = (r.nextInt(10) + 1) * 1024;
                int used1 = r.nextInt(total1);
                int free1 = total1 - used1;
                double usedPer1 = (double) used1 / (double) total1;
                memState11.setTotal(total1 + "");
                memState11.setUsed(used1 + "");
                memState11.setFree(free1 + "");
                memState11.setUsePer(String.format("%.2f", usedPer1 * 100));
                int total2 = (r.nextInt(10) + 1) * 1024;
                int used2 = r.nextInt(total2);
                int free2 = total2 - used2;
                double usedPer2 = (double) used2 / (double) total2;
                memState12.setTotal(total2 + "");
                memState12.setUsed(used2 + "");
                memState12.setFree(free2 + "");
                memState12.setUsePer(String.format("%.2f", usedPer2 * 100));
                int total3 = (r.nextInt(10) + 1) * 1024;
                int used3 = r.nextInt(total3);
                int free3 = total3 - used3;
                double usedPer3 = (double) used3 / (double) total3;
                memState13.setTotal(total3 + "");
                memState13.setUsed(used3 + "");
                memState13.setFree(free3 + "");
                memState13.setUsePer(String.format("%.2f", usedPer3 * 100));
                ops.set("MemState:" + memState11.getMemStateId() + ":" + sdf.format(date), JSONObject.toJSONString(memState11));
                ops.set("MemState:" + memState12.getMemStateId() + ":" + sdf.format(date), JSONObject.toJSONString(memState12));
                ops.set("MemState:" + memState13.getMemStateId() + ":" + sdf.format(date), JSONObject.toJSONString(memState13));

//        DeskState
                DeskState deskState11 = new DeskState(host11.getHostId(), host11.getDeskId(), "D", "", "", "", "", date);
                DeskState deskState12 = new DeskState(host12.getHostId(), host12.getDeskId(), "ROM", "", "", "", "", date);
                DeskState deskState13 = new DeskState(host13.getHostId(), host13.getDeskId(), "D", "", "", "", "", date);
                int size1 = (r.nextInt(10) + 1) * 1024;
                int use1 = r.nextInt(size1);
                int avail1 = size1 - use1;
                double usedP1 = (double) use1 / (double) size1;
                deskState11.setSize(size1 + "");
                deskState11.setUsed(use1 + "");
                deskState11.setAvail(avail1 + "");
                deskState11.setUsePer(String.format("%.2f", usedP1 * 100));
                int size2 = (r.nextInt(10) + 1) * 1024;
                int use2 = r.nextInt(size2);
                int avail2 = size2 - use2;
                double usedP2 = (double) use2 / (double) size2;
                deskState12.setSize(size2 + "");
                deskState12.setUsed(use2 + "");
                deskState12.setAvail(avail2 + "");
                deskState12.setUsePer(String.format("%.2f", usedP2 * 100));
                int size3 = (r.nextInt(10) + 1) * 1024;
                int use3 = r.nextInt(size3);
                int avail3 = size3 - use3;
                double usedP3 = (double) use3 / (double) size3;
                deskState13.setSize(size3 + "");
                deskState13.setUsed(use3 + "");
                deskState13.setAvail(avail3 + "");
                deskState13.setUsePer(String.format("%.2f", usedP3 * 100));

                ops.set("DeskState:" + deskState11.getDeskStateId() + ":" + sdf.format(date), JSONObject.toJSONString(deskState11));
                ops.set("DeskState:" + deskState12.getDeskStateId() + ":" + sdf.format(date), JSONObject.toJSONString(deskState12));
                ops.set("DeskState:" + deskState13.getDeskStateId() + ":" + sdf.format(date), JSONObject.toJSONString(deskState13));

//        TCP
                TcpState tcpState11 = new TcpState(host11.getHostId(), host11.getTcpId(), r.nextInt(100) + "", r.nextInt(100) + "", r.nextInt(100) + "", date);
                TcpState tcpState12 = new TcpState(host12.getHostId(), host12.getTcpId(), r.nextInt(100) + "", r.nextInt(100) + "", r.nextInt(100) + "", date);
                TcpState tcpState13 = new TcpState(host13.getHostId(), host13.getTcpId(), r.nextInt(100) + "", r.nextInt(100) + "", r.nextInt(100) + "", date);

                ops.set("TcpState:" + tcpState11.getTcpStateId() + ":" + sdf.format(date), JSONObject.toJSONString(tcpState11));
                ops.set("TcpState:" + tcpState12.getTcpStateId() + ":" + sdf.format(date), JSONObject.toJSONString(tcpState12));
                ops.set("TcpState:" + tcpState13.getTcpStateId() + ":" + sdf.format(date), JSONObject.toJSONString(tcpState13));

//        Netio
                NetioState netioState11 = new NetioState(host11.getHostId(), host11.getNetioId(), r.nextInt(1000) + "", r.nextInt(1000) + "", String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), r.nextInt(1000) + "", r.nextInt(1000) + "", date);
                NetioState netioState12 = new NetioState(host12.getHostId(), host12.getNetioId(), r.nextInt(1000) + "", r.nextInt(1000) + "", String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), r.nextInt(1000) + "", r.nextInt(1000) + "", date);
                NetioState netioState13 = new NetioState(host12.getHostId(), host13.getNetioId(), r.nextInt(1000) + "", r.nextInt(1000) + "", String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), String.format("%.2f", r.nextDouble() * 10000), r.nextInt(1000) + "", r.nextInt(1000) + "", date);

                ops.set("NetioState:" + netioState11.getNetioStateId() + ":" + sdf.format(date), JSONObject.toJSONString(netioState11));
                ops.set("NetioState:" + netioState12.getNetioStateId() + ":" + sdf.format(date), JSONObject.toJSONString(netioState12));
                ops.set("NetioState:" + netioState13.getNetioStateId() + ":" + sdf.format(date), JSONObject.toJSONString(netioState13));


//        创建系统进程并存入系统
                ProcessInfo process11 = new ProcessInfo(system.getProcessIds().get(0), "进程1", giveState() + "", String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", (100+r.nextInt(1000)) * 0.01), String.format("%.2f", (100+r.nextInt(1000)) * 0.01), system.getSysId(), date);
                ProcessInfo process12 = new ProcessInfo(system.getProcessIds().get(1), "进程2", giveState() + "", String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", r.nextDouble() * 20), String.format("%.2f", (100+r.nextInt(1000)) * 0.01), String.format("%.2f", (100+r.nextInt(1000)) * 0.01), system.getSysId(), date);
                ops.set("Process:" + process11.getProcessId() + ":" + sdf.format(date), JSONObject.toJSONString(process11));
                ops.set("Process:" + process12.getProcessId() + ":" + sdf.format(date), JSONObject.toJSONString(process12));

//        创建系统日志并存入
                LogInfo log11 = new LogInfo(system.getLogInfoIds().get(0), system.getSysId(), "安全审核记录", giveState()+"", date);
                LogInfo log12 = new LogInfo(system.getLogInfoIds().get(1), system.getSysId(), "系统错误记录", giveState()+"", date);
                ops.set("LogInfo:" + log11.getLogId() + ":" + sdf.format(date), JSONObject.toJSONString(log11));
                ops.set("LogInfo:" + log12.getLogId() + ":" + sdf.format(date), JSONObject.toJSONString(log12));


                ops.set("System:" + system.getSysId() + ":" + sdf.format(date), JSON.toJSONString(system));
                date.setTime(date.getTime() + 1000);

            }

            System.out.println(system.getSysId());
        }





    }

    ArrayList<String> generateID3(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(new RandomID().getUUID());
        list.add(new RandomID().getUUID());
        list.add(new RandomID().getUUID());
        return list;
    }
    ArrayList<String> generateID2(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(new RandomID().getUUID());
        list.add(new RandomID().getUUID());
        return list;
    }
    int giveState(){
        double rate = 0.85;
        double randomNumber = Math.random();
        if(randomNumber <= rate){
            return 1;
        }else{
            return 0;
        }
    }


    @Test
    void test1() throws ParseException {
        ValueOperations ops = redisTemplate.opsForValue();
        Date start = sdf.parse("2021-06-25_14:00:00");
        Date end = sdf.parse("2021-06-25_14:14:00");
        HashMap<String,Object> map = new HashMap();
        Long spread = end.getTime() - start.getTime();
        System.out.println(spread);
        int num = (int)(spread / 60000);
        System.out.println(num);

        ArrayList<SystemInfo> systems = new ArrayList<>();
        ArrayList<String> sysIds = JSONObject.parseObject((String) ops.get("SystemIds"), ArrayList.class);
        for(String id:sysIds){
            systems.add(JSONObject.parseObject((String) ops.get("System:"+id),SystemInfo.class));
        }
        for(SystemInfo system: systems){
            ArrayList<String> highMem = new ArrayList();
            Long time = start.getTime();
            Date date = new Date();
            date.setTime(time);
            //每个时间点
            for (int i = 0; i < num; i++) {
                System.out.println(date);
                System.out.println(start);
                ArrayList<HostInfo> hosts = new ArrayList<>();
                //所有主机
                for (String hostId:system.getHostIds()) {
                    hosts.add(JSONObject.parseObject((String) ops.get("Host:"+hostId+":"+sdf.format(date)),HostInfo.class));
                }
                ArrayList<String> allMem = new ArrayList<>();
                //获取系统下所有主机的最大磁盘占用
                for(HostInfo host:hosts){
                    MemState mem = JSONObject.parseObject((String) ops.get("MemState:"+host.getMemId()+":"+sdf.format(date)),MemState.class);
                    System.out.println(mem);
                    allMem.add(mem.getUsePer());
                }
                //判断最大
                while(allMem.size()>1){
                    if(Double.parseDouble(allMem.get(0)) > Double.parseDouble(allMem.get(1)))
                        allMem.remove(1);
                    else
                        allMem.remove(0);
                }
                highMem.add(allMem.get(0));
                date.setTime(date.getTime() + 60000);
            }
            map.put(system.getSysName(),highMem);
        }

        System.out.println(map);
    }


    @Test
    void test2() throws ParseException {

        ValueOperations ops = redisTemplate.opsForValue();
        //先查对应id和创建时间的sys，找到对应模块id,然后根据id和创建时间查询，添加到结果中
        for(int i=10;i<59;i++) {
            ArrayList<HostInfo> hostList = new ArrayList<>();//测试时System后面跟test1输出的某一个id

            SystemInfo system = JSONObject.parseObject((String) ops.get("System:444557ba1abd45cc858fb199b79d01d0:2021-05-18_00:00:"+i+""), SystemInfo.class);

//            SystemInfo system = JSONObject.parseObject((String) ops.get("System:891032ebeb274ef58bf7af3e54b88208:2021-05-18_00:00:"+i+""), SystemInfo.class);
//            System.out.println(system);
            int count = 0;
            for (String hostId : system.getHostIds()) {
                hostList.add(JSONObject.parseObject((String) ops.get("Host:" + hostId + ":2021-05-18_00:00:"+i+""), HostInfo.class));

//                System.out.println(host);
//                LoadState load = JSONObject.parseObject((String) ops.get("LoadState:"+host.getLoadId()+":2021-05-18 00:00:"+i+""),LoadState.class);
//                System.out.println(load);
//                CpuState cpu = JSONObject.parseObject((String) ops.get("CpuState:"+host.getCpuId()+":2021-05-18 00:00:"+i+""),CpuState.class);
//                System.out.println(cpu);
//                MemState mem = JSONObject.parseObject((String) ops.get("MemState:"+host.getMemId()+":2021-05-18 00:00:"+i+""),MemState.class);
//                System.out.println(mem);
//                DeskState desk = JSONObject.parseObject((String) ops.get("DeskState:"+host.getDeskId()+":2021-05-18 00:00:"+i+""),DeskState.class);
//                System.out.println(desk);
//                TcpState tcp = JSONObject.parseObject((String) ops.get("TcpState:"+host.getTcpId()+":2021-05-18 00:00:"+i+""),TcpState.class);
//                System.out.println(tcp);
//                NetioState netio = JSONObject.parseObject((String) ops.get("NetioState:"+host.getNetioId()+":2021-05-18 00:00:"+i+""),NetioState.class);
//                System.out.println(netio);
//                System.out.println();

            }
            for (HostInfo process : hostList) {
                System.out.println(process.getHostState());
                if (process.getHostState().equals("1")) {
                    count += 1;
//                    System.out.println(process.getHostState());
                }
            }
            System.out.println(JSON.toJSONString(count) + "/" + JSON.toJSONString(hostList.size()));
        }

    }

    @Test
    public void test3() throws ParserException {
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
        System.out.println(res);
    }




}
