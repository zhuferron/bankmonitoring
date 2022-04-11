<template>
  <div>
    <strong style="font-size:30px;">主机负载监控</strong>
    <br>
    <br>
    <div class="block">
      <span class="demonstration">设置默认时间</span>
      <el-date-picker
          v-model="params.createTime"
          type="date"
          placeholder="选择日期">
      </el-date-picker>
      <el-button type="text" @click="getTableData">搜索</el-button>
    </div>
    <div>
      <el-table :data="dataShow" style="max-width: 100%">
        <el-table-column prop="hostId" label="主机ID" align="center">
        </el-table-column>
        <el-table-column prop="loadId" label="负载ID" align="center">
        </el-table-column>
        <el-table-column prop="oneLoad" label="一分钟前负载" align="center">
        </el-table-column>
        <el-table-column prop="fiveLoad" label="五分钟前负载" align="center">
        </el-table-column>
        <el-table-column prop="fifteenLoad" label="十五分钟前负载" align="center">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center">
        </el-table-column>
      </el-table>
    </div>
    <div style="padding-top: 3%">
      <el-pagination background layout="prev, pager, next" :page-count="pageNum" :current-page="currentPage"  @current-change="curPage" @prev-click="prePage" @next-click="nextPage"></el-pagination>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import * as echarts from "echarts";
import Moment from "moment";
export default {
  data() {
    return {
      data: [],
      totalPage: [],
      // 每页显示数量
      pageSize: 10,
      // 共几页
      pageNum: 50,
      // 当前显示的数据
      dataShow: [],
      // 默认当前显示第一页
      currentPage: 1,
      params: {
        createTime: null,
        hosdtId: null
      },
    }
  },
  created() {
    this.getTableData();
  },
  methods:{
    async getTableData() {
      const {data: res} = await this.$http.get('http://192.168.43.73:9002/getLoadStateByTime/'+ '2021-06-25_14:00:00')  //访问后台

      if(res.code==200){
        this.$message.success(res.msg);
        this.data=JSON.parse(res.data);
        for(var i = 0; i < this.data.length; i++){
          this.data[i].createTime=this.getFullTime(this.data[i].createTime)
        };


        setTimeout(()=>{
          let date=new Date(this.params.createTime);
          date=moment(date).format('YYYY-MM-DD');
          if(this.params.createTime){
            this.dataShow=this.dataShow.filter((r)=>{
              return this.chooseYMD(r.createTime)==date;
            });
          }
        },500);

        //分页功能实现
        this.pageNum = Math.ceil(this.data.length / this.pageSize) || 1;
        for (let i = 1; i < this.pageNum+1; i++) {
          this.totalPage[i] = this.data.slice(this.pageSize * (i-1), this.pageSize * i)
        }
        // 获取到数据后显示第一页内容
        this.dataShow = this.totalPage[this.currentPage];
      }else{
        this.$message.error(res.msg);
      }
    },
    //下一页
    nextPage() {
      if (this.currentPage === this.pageNum - 1) return ;
      this.dataShow = this.totalPage[++this.currentPage];
    },
    // 上一页
    prePage() {
      if (this.currentPage === 0) return ;
      this.dataShow = this.totalPage[--this.currentPage];
    },

    curPage(val){
      this.currentPage = val;
      this.dataShow = this.totalPage[this.currentPage];
    },

    setValueNull(val) {
      this.params.sysId=null;
    },
    getFullTime(timeStamp) {
      const stamp = new Date(timeStamp);
      const time = Moment(stamp).format('YYYY-MM-DD HH:mm:ss');
      return time;
    },
    chooseYMD(times){
      var timearr = times.replace(" ", ":").replace(/\:/g, "-").split("-");
      var timestr = ""+timearr[0]+"-" + timearr[1] + "-" + timearr[2]
      return timestr
    }

  }
}
</script>