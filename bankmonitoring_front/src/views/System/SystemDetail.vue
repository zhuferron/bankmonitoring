<template>
  <div>
    <strong style="font-size:30px;">---系统概况---</strong>
    <div style="padding-bottom: 2%;">
      <el-row :gutter="20" style="padding-left: 2%;padding-top: 2%">
        <el-col :span="6">
          <el-card style="background-color: #968ece;height: 250px">
            <div class="box-text">
              <div>
                <div style="float: right;padding-right: 10%">
                  <i style="font-size: 100px" class="el-icon-monitor"></i>
                </div>
                <p style="font-size: 250%">{{HostRatio}}</p>
                <p style="font-size: 120%">主机在线</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card style="background-color: #82cddb;height: 250px">
            <div class="box-text">
              <div style="float: right;padding-right: 20%;padding-bottom: 50%">
                <i style="font-size: 100px" class="el-icon-coin"></i>
              </div>
              <p style="font-size: 250%">{{ DbRatio}}</p>
              <p style="font-size: 120%">数据库</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card style="background-color: #a0e59a;height: 250px">
            <div class="box-text">
              <div style="float: right;padding-right: 20%;padding-bottom: 50%">
                <i style="font-size: 100px" class="el-icon-data-analysis"></i>
              </div>
              <p style="font-size: 250%">{{ProcessRatio}}</p>
              <p style="font-size: 120%">进程</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card style="background-color: #66bae7;height: 250px">
            <div class="box-text">
              <div style="float: right;padding-right: 20%;padding-bottom: 50%">
                <i style="font-size: 100px" class="el-icon-notebook-1"></i>
              </div>
              <p style="font-size: 250%">{{ LogNumber}}</p>
              <p style="font-size: 120%">日志总数</p>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-row style="padding-left: 2%;padding-top: 2%">
        <el-col :span="4">
          <el-progress type="circle" :percentage="80" status="exception"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最高CPU使用率：80%
            </el-tag>
          </div>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :percentage="50" status="success"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最高CPU等待率：50%
            </el-tag>
          </div>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :percentage="70" status="warning"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最高CPU空闲率：70%
            </el-tag>
          </div>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :percentage="75" status="exception"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最高内存使用率：75%
            </el-tag>
          </div>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :percentage="20" status="warning"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最低内存使用率：20%
            </el-tag>
          </div>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :percentage="90" status="exception"></el-progress>
          <div style="padding-top: 8%">
            <el-tag size="medium">
              最高磁盘占用率：90%
            </el-tag>
          </div>
        </el-col>
      </el-row>
    </div>
    <strong style="font-size:30px;">---系统上周各时间段故障情况---</strong>
    <div :style="{width: '100%', height:'500px',paddingLeft:'2%'}">
      <TimeException></TimeException>
    </div>
    <div style="padding-top: 2%;padding-left: 88%"><el-button type="primary" @click="deleteSystemId">返回系统概览界面</el-button></div>

<!--    <div align="center" style="padding-top: 5%">-->
<!--      <el-table :data="ProcessInfo" style="width: 90%;font-size: large" border>-->
<!--        <el-table-column prop="processId" label="进程ID" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="processName" label="进程名称" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column label="状态" align="center">-->
<!--          <template scope="scope">-->
<!--            <el-tag :prop="scope.row.processState" :type="setType(scope.row.processState)" size="large">{{scope.row.processState}}</el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="sysId" label="系统ID" align="center">-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--      <el-table :data="LogInfo" style="width:90%;font-size: large" border>-->
<!--        <el-table-column prop="logId" label="日志ID" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="state" label="日志状态" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="infoContent" label="日志内容" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="sysId" label="系统ID" align="center">-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--      <el-table :data="HostInfo" border style="width: 90%;font-size: large" border>-->
<!--        <el-table-column prop="ip" label="主机IP" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="hostId" label="主机ID" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="hostName" label="主机名称" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column label="状态" align="center">-->
<!--          <template scope="scope">-->
<!--            <el-tag :prop="scope.row.hostState" :type="setType(scope.row.hostState)" size="large">{{scope.row.hostState}}</el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="sysId" label="系统ID" align="center">-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--      <el-table :data="DbInfo" style="width: 90%;font-size: large"  border>-->
<!--        <el-table-column prop="dbId" label="数据库ID" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="dbName" label="数据库名称" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="dbType" label="数据库类型" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="port" label="数据库端口" align="center">-->
<!--        </el-table-column>-->
<!--        <el-table-column label="状态" align="center">-->
<!--          <template scope="scope">-->
<!--            <el-tag :prop="scope.row.dbState" :type="setType(scope.row.dbState)" size="large">{{scope.row.dbState}}</el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column prop="sysId" label="系统ID" align="center">-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--    </div>-->
  </div>


</template>

<script>
  import Header from "@/components/common/Header";
  import TimeException from "@/components/echarts/TimeException";

  export default {
    name: "System",
    components: {TimeException, Header},

    data(){
      return{
        // HostInfo:[],
        // ProcessInfo:[],
        // LogInfo:[],
        // DbInfo:[],
        HostRatio:'',
        ProcessRatio:'',
        LogNumber:'',
        DbRatio:'',
      }
    },
    created() {
      this.getData();
    },
    methods:{
      async getData(){
        // const {data: res1} = await this.$http.get('http://192.168.43.73:9002/getSystemHostInfoByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-05-18_00:00:00')  //访问后台
        // const {data: res2} = await this.$http.get('http://192.168.43.73:9002/getSystemProcessInfoByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-05-18_00:00:00')
        // const {data: res3} = await this.$http.get('http://192.168.43.73:9002/getSystemLogInfoByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-05-18_00:00:00')
        // const {data: res4} = await this.$http.get('http://192.168.43.73:9002/getSystemDbInfoByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-05-18_00:00:00')
        const {data: res5} = await this.$http.get('http://192.168.43.73:9002/getHostSumByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-06-25_14:00:00')
        const {data: res6} = await this.$http.get('http://192.168.43.73:9002/getDbSumByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-06-25_14:00:00')
        const {data: res7} = await this.$http.get('http://192.168.43.73:9002/getProcessSumByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-06-25_14:00:00')
        const {data: res8} = await this.$http.get('http://192.168.43.73:9002/getLogSumByIdAndTime/' + window.sessionStorage.getItem("systemId",) + '/2021-06-25_14:00:00')
        if(res5.code==200){
          // this.$message.success(res1.msg);  //信息提示
          // this.HostInfo=JSON.parse(res1.data);
          // this.ProcessInfo=JSON.parse(res2.data);
          // this.LogInfo=JSON.parse(res3.data);
          // this.DbInfo = JSON.parse(res4.data);
          this.HostRatio = res5.data;
          this.DbRatio = res6.data;
          this.ProcessRatio = res7.data;
          this.LogNumber = res8.data;
        }
      },
      setType(number){
        var state = "success";
        if(number==0) {return state="info"}
        return state;
      },
      setColor(percentage){
        var state = "success";
        if(percentage>50&&percentage<80){return state="warning"}
        if(percentage>=80){return state="exception"}
        return state;
      },
      getNumber(str){
        return str=str.replaceAll("%","");
      },
      deleteSystemId(){
        sessionStorage.removeItem("systemId")
        this.$router.push({path: "/System"})
      }
    }
  }
</script>


<style>

.box-text{
  text-align: left;
  padding-left: 10%;
  color: #6085b6;
}
.box-icon{
}
</style>