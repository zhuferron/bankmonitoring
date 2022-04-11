<template>
  <div>
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
      <el-table :data="dataShow" style="width: 100%">
        <el-table-column prop="hostId" label="主机ID" align="center">
        </el-table-column>
        <el-table-column prop="netioStateId" label="联网状态" align="center">
        </el-table-column>
        <el-table-column  label="数据包" align="center">
          <template scope="scope">
            <el-button type="primary"size="small" @click="showPackageChart(scope.row.rxpck,scope.row.txpck)">查看数据包数据</el-button>
          </template>
        </el-table-column>
        <el-table-column  label="发送接收" align="center">
                    <template scope="scope">
                    <el-button type="primary" size="small" @click="showSendReceiveChart(scope.row.rxbyt,scope.row.txbyt)">查看发送接收数据</el-button>
                    </template>
                  </el-table-column>
        <el-table-column  label="压缩数据包" align="center">
                    <template scope="scope">
                    <el-button type="primary" size="small" @click="showCompressChart(scope.row.rxcmp,scope.row.txcmp)">查看压缩数据包数据</el-button>
                    </template>
                  </el-table-column>
        <el-table-column prop="createTime" label="查询时间" align="center">
        </el-table-column>
        <el-table-column prop="rxmcst" label="每秒接收的多播数据包" align="center">
        </el-table-column>
      </el-table>
    </div>
    <el-row>
      <el-col :span="8"><div id="myChart1" :style="{width: '500px', height: '300px'}" ></div></el-col>
      <el-col :span="8"><div id="myChart2" :style="{width: '500px', height: '300px'}"></div></el-col>
      <el-col :span="8"><div id="myChart3" :style="{width: '500px', height: '300px'}"></div></el-col>
    </el-row>
    <div style="padding-top: 3%">
      <el-pagination background layout="prev, pager, next" :page-count="pageNum" :current-page="currentPage"  @current-change="curPage" @prev-click="prePage" @next-click="nextPage"></el-pagination>
    </div>
  </div>

</template>

<script>
import moment from "moment";
import * as echarts from "echarts";
import Moment from "moment";
export default {
  data() {
    return {
      data: [],
      totalPage: [],
      // 每页显示数量
      pageSize: 8,
      // 共几页
      pageNum: 50,
      // 当前显示的数据
      dataShow: [],
      // 默认当前显示第一页
      currentPage: 1,
      params: {
        createTime: null,
        hostId: null
      },
      options: [{
        value: 'host1',
        label: 'host1'
      }, {
        value: 'host2',
        label: 'host2'
      }, {
        value: 'host3',
        label: 'host3'
      }]
    }
  },
  created() {
    this.getTableData();
  },

  methods: {
    showPackageChart(r,t){
      var myChart1 = this.$echarts.init(document.getElementById('myChart1'))
      r=parseInt(r);
      t=parseInt(t);
      var useData=[r,t]
      myChart1.setOption(
          {title: {
              text: '数据包',
            },
            xAxis: {
              type: 'category',
              boundaryGap: true,
              data: ['每秒接收数据包', '每秒发送数据包']
            },
            yAxis: {
              type: 'value',
              name:"数据包"
            },
            series: [
              {data: [
                  {
                  value: r,
                  itemStyle: {
                    color: '#ff507f'
                  }},
                  {
                  value: t,
                  itemStyle: {
                    color: '#ffae4b'
                  }}
                  ],
                type: 'bar'

              }]
          }
      );
},
    showSendReceiveChart(s,r){
      var myChart2 = this.$echarts.init(document.getElementById('myChart2'))
      s=parseInt(s);
      r=parseInt(r);
      var useData=[s,r]
      myChart2.setOption(
          {
            title: {
              text: '网速',
            },
            xAxis: {
              type: 'category',
              boundaryGap: true,
              data: ['每秒接收kb数', '每秒发送kb数']
            },
            yAxis: {
              type: 'value',
              name:'网速kb/s'
            },
            series: [
              {data: [{
                  value: s,
                  itemStyle: {
                    color: '#3ec15e'
                  }},
              {
                value:r,
                itemStyle: {
                  color: '#79c4fd'
                }}],
                type: 'bar'

              }]
          }
      );

    },
    showCompressChart(r,t){
      var myChart3 = this.$echarts.init(document.getElementById('myChart3'))
      r=parseInt(r);
      t=parseInt(t);
      myChart3.setOption(
          {
            title: {
              text: '压缩数据包',
            },
            xAxis: {
              type: 'category',
              boundaryGap: true,
              data: ['每秒接收压缩数据包', '每秒发送压缩数据包']
            },
            yAxis: {
              type: 'value',
              name:'压缩数据包数'
            },
            series: [
              {data: [{
                  value: r,
                  itemStyle: {
                    color: '#a662f6'
                  }},
                  {
                    value:t,
                    itemStyle: {
                      color: '#fde406'
                    }}],
                type: 'bar'

              }]
          }
      );
    },
    async getTableData() {

      const {data: res} = await this.$http.get('http://192.168.43.73:9002/getNetioStateByTime/'+ '2021-06-25_14:00:00')  //访问后台
      if(res.code==200){
        this.$message.success(res.msg);  //信息提示
        this.data=JSON.parse(res.data);
        for(var i = 0; i < this.data.length; i++){
          this.data[i].createTime=this.getFullTime(this.data[i].createTime)
        };

      }

      setTimeout(() => {
        let date = new Date(this.params.createTime);
        date=moment(date).format('YYYY-MM-DD');
        if (this.params.createTime) {
          this.dataShow = this.dataShow.filter((r) => {
            let row = new Date(r.createTime);
            return this.chooseYMD(r.createTime) == date;
          });
        }
      }, 500);
      //分页功能实现
      this.pageNum = Math.ceil(this.data.length / this.pageSize) || 1;
      for (let i = 1; i < this.pageNum+1; i++) {
        this.totalPage[i] = this.data.slice(this.pageSize * (i-1), this.pageSize * i)
      }
      // 获取到数据后显示第一页内容
      this.dataShow = this.totalPage[this.currentPage];
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
      this.params.hostId=null;
    },
    chooseYMD(times){
      var timearr = times.replace(" ", ":").replace(/\:/g, "-").split("-");
      var timestr = ""+timearr[0]+"-" + timearr[1] + "-" + timearr[2]
      return timestr
    },
    getFullTime(timeStamp) {
      const stamp = new Date(timeStamp);
      const time = Moment(stamp).format('YYYY-MM-DD HH:mm:ss');
      return time;
    },


  }

}

</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 600px;
}
</style>